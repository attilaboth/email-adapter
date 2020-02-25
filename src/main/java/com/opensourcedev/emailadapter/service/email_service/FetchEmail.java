package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.service.crud_service.CrudService;
import com.opensourcedev.emailadapter.service.email_server_configuration_service.EmailServerConfigurer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Service
public class FetchEmail {

    private EmailServerConfigurer serverConfigurer;
    private CrudService crudService;

    private Store store;
    private Message[] messages;

    @Autowired
    public FetchEmail(EmailServerConfigurer serverConfigurer, @Qualifier("emailCrudServiceImpl") CrudService crudService) {
        this.serverConfigurer = serverConfigurer;
        this.crudService = crudService;
    }


    public void fetchMailsInMailbox(boolean subfolderLookup){
        int emailNumber = 1;
        try {

            String emailRootFolder = serverConfigurer.getConnectionProperties().get(4);
            String emailSubFolder = serverConfigurer.getConnectionProperties().get(5);

            store = serverConfigurer.connectToMailbox();

            if (subfolderLookup){
                Folder emailFolder = store.getFolder(emailRootFolder).getFolder(emailSubFolder);
                emailFolder.open(Folder.READ_ONLY);
                messages = emailFolder.getMessages();
                storeEmailContent(emailNumber);

            }else {
                Folder emailFolder = store.getFolder(emailRootFolder);
                emailFolder.open(Folder.READ_ONLY);

                messages = emailFolder.getMessages();
                storeEmailContent(emailNumber);
            }

        }catch (MessagingException e){
            log.debug("[!!] Error during connection to store...");
            e.printStackTrace();
            e.getMessage();
        }finally {
            if (store != null){
                try {
                    log.debug("[*] Closing the Store session...");
                    store.close();
                }catch (MessagingException e){
                    log.debug("[!!] Error calling the \"store.close()\" session");
                    e.printStackTrace();
                }

            }
        }

    }

    @Transactional
    public void storeEmailContent(int emailNumber) throws MessagingException {
        for(Message message : messages){
            EmailDTO email = new EmailDTO();
            email.setSubject(message.getSubject());
            email.setSender(InternetAddress.toString(message.getFrom())); //need to decompose Address object to be displayed properly
            email.setRecipient(InternetAddress.toString(message.getAllRecipients()));
            email.setBodyAsString(storeStringContent(message));
            email.setImageAttachment(storeContent(message));
            email.setTextAttachment(storeContent(message));

            crudService.save(email);

            log.debug("[*] Saving email to DB..." );
            log.debug("[*] Saved email: " + emailNumber + " contains: " + email.toString());
            emailNumber += 1;
        }
    }

    private byte[] storeContent(Part part){
        try {
            if (part.isMimeType("multipart/*")){
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                Multipart mp = (Multipart) part.getContent();
                mp.writeTo(buffer);
                byte[] multipart = buffer.toByteArray();
                log.debug("[*] Content type: " + part.getContentType());
                return multipart;
            }else if(part.isMimeType("image/jpeg")){
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                Multipart mp = (Multipart) part.getContent();
                mp.writeTo(buffer);
                byte[] imageBytes = buffer.toByteArray();
                log.debug("[*] Content type: " + part.getContentType());
                return imageBytes;
            }

        }catch (MessagingException e){
            log.debug("[!!] Error checking mime type...");
            e.getMessage();
            e.printStackTrace();
        }catch (IOException e){
            log.debug("[!!] Error getting body and content from email...");
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }

    private String storeStringContent(Part part){
        try {
            if (part.isMimeType("text/plain")){
                log.debug("[*] Content type: " + part.getContentType());
                return part.getContent().toString();
            }
        }catch (MessagingException e){
            log.debug("[!!] Error checking mime type...");
            e.getMessage();
            e.printStackTrace();
        }catch (IOException e){
            log.debug("[!!] Error getting body and content from email...");
            e.printStackTrace();
            e.getMessage();
        }
        return null;
    }


}
