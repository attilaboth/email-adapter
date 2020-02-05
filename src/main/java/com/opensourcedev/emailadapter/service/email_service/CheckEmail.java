package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.service.email_server_configuration_service.MailServerConnection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Slf4j
@Service
public class CheckEmail {

    @Autowired
    public CheckEmail(MailServerConnection serverConfigurer) {
        this.serverConfigurer = serverConfigurer;
    }



    private MailServerConnection serverConfigurer;
    private Store store;
    private String password = serverConfigurer.getConnectionProperties().get(2);;
    private String username = serverConfigurer.getConnectionProperties().get(1);;
    private String host = serverConfigurer.getConnectionProperties().get(0);;
    private final String imapProtocol = serverConfigurer.getConnectionProperties().get(3);
    private final String emailFolder = serverConfigurer.getConnectionProperties().get(4);







    public void checkMailsInMailbox(){

        try{



            store = serverConfigurer.connectToMailbox().getStore(imapProtocol);
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder(this.emailFolder);
            emailFolder.open(Folder.READ_ONLY);

            // TODO iterate through emails and display them


        }catch (NoSuchProviderException e){
            log.debug("[!!] Error initializing the Store object in \"CheckEmail class\" " +
                    "calling the \"checkMailsInMailbox()\"");
            e.printStackTrace();
        }catch (MessagingException e){
            log.debug("[!!] Error during connection to store...");
            e.printStackTrace();
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


    @Override
    public String toString() {
        return "CheckEmail{" +
                "serverConfigurer=" + serverConfigurer +
                ", store=" + store +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", imapProtocol='" + imapProtocol + '\'' +
                ", emailFolder='" + emailFolder + '\'' +
                '}';
    }
}
