package com.opensourcedev.emailadapter.service.email_server_configuration_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.mail.Session;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailServerConfigurerImplTest {


    @Mock
    EmailServerConfigurer serverConfigurer;

    @InjectMocks
    EmailServerConfigurerImpl emailServerConfigurerImpl;

    private String password = "Pass";
    private String username = "admin";
    private String host = "imap.gmail.com";
    private String imapProtocol = "imap";
    private String emailRootFolder = "index";
    private String emailSubFolder = "subFolder";
    private String portAddress = "mail.imap.port";
    private String imapPort = "993";
    private String ttlsEnale = "mail.imap.starttls.enable";
    private boolean startTTLS = true;
    private List<String> connectionProps;


    @BeforeEach
    void setup() {
        connectionProps.add(password);
        connectionProps.add(username);
        connectionProps.add(host);
        connectionProps.add(imapProtocol);
        connectionProps.add(emailRootFolder);
        connectionProps.add(emailSubFolder);

        Properties properties = new Properties();
        properties.put(imapProtocol, host);
        properties.put(portAddress, imapPort);
        properties.put(ttlsEnale, startTTLS);

        Session session = Session.getDefaultInstance(properties);
    }

    @Test
    void getConnectionProperties() {
        List<String> connProps = new ArrayList<>();
        connProps.add("Pass");
        connProps.add("admin");
        connProps.add("imap.google.com");
        connProps.add("imap");
        connProps.add("index");
        connProps.add("subFolder");

        when(emailServerConfigurerImpl.getConnectionProperties()).thenReturn(connProps);

        assertEquals(connProps, connectionProps);
        assertNotNull(connProps);
        assertNotNull(connectionProps);

    }

    @Test
    void connectToMailbox() {
        Properties props = new Properties();
        props.put("imap", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.starttls.enable", true);

        Session emailSession = Session.getDefaultInstance(props);

        when(emailServerConfigurerImpl.connectToMailbox()).thenReturn(emailSession);

        assertNotNull(props);
    }
}