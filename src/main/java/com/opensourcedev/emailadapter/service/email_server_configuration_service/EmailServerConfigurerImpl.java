package com.opensourcedev.emailadapter.service.email_server_configuration_service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@PropertySource("classpath:application-SMTP.properties")
@Service
public class EmailServerConfigurerImpl implements EmailServerConfigurer {

    @Value("${com.opensourcedev.emailadapter.imap.host}")
    private String imapHost;

    @Value("${com.opensourcedev.emailadapter.imap.port}")
    private int imapPort;

    @Value("${com.opensourcedev.emailadapter.imap.protocol}")
    private String imapProtocol;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean startTTLS;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${com.opensourcedev.emailadapter.emailfolder}")
    private String emailFolder;

    @Value("${com.opensourcedev.emailadapter.subfolder}")
    private String subfolder;


    private final String portAddress = "mail.imap.port";
    private final String ttlsEnable = "mail.imap.starttls.enable";
    private Store store;

    private final String storeProtocolProperty = "mail.store.protocol";


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Override
    public List<String> getConnectionProperties() {
        List<String> connectionProps = new ArrayList<>();

        connectionProps.add(imapHost);
        connectionProps.add(username);
        connectionProps.add(password);
        connectionProps.add(imapProtocol);
        connectionProps.add(emailFolder);
        connectionProps.add(subfolder);

        if (connectionProps.contains(null)){
            log.debug("[!!] No connection properties were found!!");
            connectionProps.add("");
            return connectionProps;
        }else {
            return connectionProps;
        }


    }

    @Override
    public Store connectToMailbox() {
        Properties props = new Properties();

        props.put(storeProtocolProperty, imapProtocol);

        Session emailSession = Session.getDefaultInstance(props);
        try {
            store = emailSession.getStore(imapProtocol);
            store.connect(imapHost, username, password);

        }catch (NoSuchProviderException e){
            log.debug("[!!] Error initializing the Store object in \"EmailServerConfigurerImpl class\" ");
            e.printStackTrace();
            e.getMessage();
        }catch (MessagingException e){
            log.debug("[!!] Error during connection to store...");
            e.printStackTrace();
            e.getMessage();
        }



        log.debug("[*] emailSession created using \"connectToMailbox()\" method call in " +
                "\"EmailServerConfigurer\" class");

        return store;
    }








    @Override
    public String toString() {
        return "EmailServerConfigurer{" +
                "imapHost='" + imapHost + '\'' +
                ", imapPort=" + imapPort +
                ", imapProtocol='" + imapProtocol + '\'' +
                ", startTTLS=" + startTTLS +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailFolder='" + emailFolder + '\'' +
                ", portAddress='" + portAddress + '\'' +
                ", ttlsEnable='" + ttlsEnable + '\'' +
                '}';
    }
}
