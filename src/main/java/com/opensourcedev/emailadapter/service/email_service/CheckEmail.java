package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.service.email_server_configuration_service.EmailServerConfigurer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Service
public class CheckEmail {

    private EmailServerConfigurer serverConfigurer;
    private Store store;
    private  Message[] messages;
    private String password = "";
    private String username = "";
    private String host = "";
    private String imapProtocol = "";
    private String emailRootFolder = "";
    private String emailSubFolder = "";

    @Autowired
    public CheckEmail(EmailServerConfigurer serverConfigurer) {
        this.serverConfigurer = serverConfigurer;
    }



    // TODO in controller URL must contain boolean for subFolder lookup
    public Message[] checkAllMailsInMailbox(boolean subfolderLookup){

        try{

            emailRootFolder = serverConfigurer.getConnectionProperties().get(4);
            emailSubFolder = serverConfigurer.getConnectionProperties().get(5);

            store = serverConfigurer.connectToMailbox();

            if (subfolderLookup){
                Folder emailFolder = store.getFolder(emailRootFolder).getFolder(emailSubFolder);
                emailFolder.open(Folder.READ_ONLY);
                messages = emailFolder.getMessages();
                log.debug("[*] Total messages in subfolder " + emailSubFolder + ":" + messages.length);
                messageLookup(emailFolder);
            }else{
                Folder emailFolder = store.getFolder(emailRootFolder);
                emailFolder.open(Folder.READ_ONLY);
                messages = emailFolder.getMessages();
                log.debug("[*] Total messages in folder: " + messages.length);
                messageLookup(emailFolder);
            }





        }catch (NoSuchProviderException e){
            log.debug("[!!] Error initializing the Store object in \"CheckEmail class\" " +
                    "calling the \"checkMailsInMailbox()\"");
            e.printStackTrace();
            e.getMessage();
        }catch (MessagingException e){
            log.debug("[!!] Error during connection to store...");
            e.printStackTrace();
            e.getMessage();
        }catch (IOException e){
            log.debug("[!!] Error reading the message content from \"message.getContent()\"...");
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


        if (messages.length != 0){
            return messages;
        }else {
            log.debug("[*] No messages were found at specified root folder / subfolder");
            return messages;
        }

    }

    private void messageLookup(Folder emailFolder) throws MessagingException, IOException {
        for (int i=0; i<messages.length; i++){

            Message message = messages[i];
            log.debug("Email Number " + (i+1) + ":");
            log.debug("Subject: " + message.getSubject());
            log.debug("From: " + message.getFrom().toString());
            log.debug("Recipients: " + message.getAllRecipients().toString());
            log.debug("Body: " + message.getContent().toString());
            log.debug("Send Date: " + message.getSentDate().toString());
        }

        emailFolder.close();
    }


    @Override
    public String toString() {
        return "CheckEmail{" +
                "serverConfigurer=" + serverConfigurer +
                ", store=" + store +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", imapProtocol='" + imapProtocol + '\'' +
                ", emailRootFolder='" + emailRootFolder + '\'' +
                '}';
    }
}
