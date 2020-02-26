package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.service.email_server_configuration_service.EmailServerConfigurer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private Message[] messages;
    private List<String> messagesContent  = new ArrayList<>();
    private List<String> messagesAsString = new ArrayList<>();
    private String password = "";
    private String username = "";
    private String host = "";
    private String imapProtocol = "";


    @Autowired
    public CheckEmail(EmailServerConfigurer serverConfigurer) {
        this.serverConfigurer = serverConfigurer;
    }



    public List<String> checkAllMailsInMailbox(boolean subfolderLookup){

        try{

            String emailRootFolder = serverConfigurer.getConnectionProperties().get(4);
            String emailSubFolder = serverConfigurer.getConnectionProperties().get(5);

            store = serverConfigurer.connectToMailbox();

            if (subfolderLookup){
                Folder emailFolder = store.getFolder(emailRootFolder).getFolder(emailSubFolder);
                emailFolder.open(Folder.READ_ONLY);
                messages = emailFolder.getMessages();
                log.debug("[*] Total messages in subfolder " + emailSubFolder + ":" + messages.length);
                messagesAsString = messageLookup(emailFolder);
            }else{
                Folder emailFolder = store.getFolder(emailRootFolder);
                emailFolder.open(Folder.READ_ONLY);
                messages = emailFolder.getMessages();
                log.debug("[*] Total messages in folder: " + messages.length);
                messagesAsString = messageLookup(emailFolder);
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
            return messagesAsString;
        }else {
            log.debug("[*] No messages were found at specified root folder / subfolder");
            return messagesAsString;
        }

    }

    private List<String> messageLookup(Folder emailFolder) throws MessagingException, IOException {
        for (int i=0; i<messages.length; i++){

            Message message = messages[i];

            messagesContent.add("Email Number: " + (i+1) + ":");
            messagesContent.add("Subject: " + message.getSubject());
            messagesContent.add("From: " + message.getFrom().toString());
            messagesContent.add("Recipients: " + message.getAllRecipients().toString());
            messagesContent.add("Body: " + message.getContent().toString());
            messagesContent.add("Send Date: " + message.getSentDate().toString());

            log.debug("Email Number " + (i+1) + ":");
            log.debug("Subject: " + message.getSubject());
            log.debug("From: " + message.getFrom().toString());
            log.debug("Recipients: " + message.getAllRecipients().toString());
            log.debug("Body: " + message.getContent().toString());
            log.debug("Send Date: " + message.getSentDate().toString());
        }

        emailFolder.close();
        return messagesContent;
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
                '}';
    }
}
