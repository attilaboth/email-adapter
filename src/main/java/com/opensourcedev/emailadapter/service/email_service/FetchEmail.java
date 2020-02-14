package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.service.crud_service.CrudService;
import com.opensourcedev.emailadapter.service.email_server_configuration_service.EmailServerConfigurer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Service
public class FetchEmail {

    private EmailServerConfigurer serverConfigurer;
    @Autowired
    @Qualifier("emailCrudServiceImpl")
    private CrudService crudService;

    private Store store;
    private Message[] messages;

    @Autowired
    public FetchEmail(EmailServerConfigurer serverConfigurer) {
        this.serverConfigurer = serverConfigurer;
    }


    public Message[] fetchMailsInMailbox(boolean subfolderLookup){

        try {
            String password = serverConfigurer.getConnectionProperties().get(2);
            String username = serverConfigurer.getConnectionProperties().get(1);
            String host = serverConfigurer.getConnectionProperties().get(0);
            String imapProtocol = serverConfigurer.getConnectionProperties().get(3);
            String emailRootFolder = serverConfigurer.getConnectionProperties().get(4);
            String emailSubFolder = serverConfigurer.getConnectionProperties().get(5);

            store = serverConfigurer.connectToMailbox().getStore(imapProtocol);
            store.connect(host, username, password);

            if (subfolderLookup){
                Folder emailFolder = store.getFolder(emailRootFolder).getFolder(emailSubFolder);
                emailFolder.open(Folder.READ_ONLY);

                messages = emailFolder.getMessages();
                //TODO use repositories to store each mail


            }else {

            }



        }catch (NoSuchProviderException e){
            log.debug("[!!] Error initializing the Store object in \"FetchEmail class\" " +
                    "calling the \"fetchMailsInMailbox()\"");
            e.printStackTrace();
            e.getMessage();
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



        return messages;
    }

}
