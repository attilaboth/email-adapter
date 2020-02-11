package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.service.email_server_configuration_service.EmailServerConfigurer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckEmailTest {

    @Mock
    EmailServerConfigurer serverConfigurer;

    @InjectMocks
    CheckEmail checkEmail;

    String password = "";
    String username = "";
    String host = "";
    String imapProtocol = "";
    String emailRootFolder = "";
    String emailSubFolder = "";
    Message message;
    Address address;

    @BeforeEach
    void setup() throws MessagingException {
        password = "PassWord";
        username = "admin";
        host = "imap.gmail.com";
        imapProtocol = "imap";
        emailRootFolder = "index";
        emailSubFolder = "subFolder";

    }


    @Test
    void checkAllMailsInMailbox() {

        when(checkEmail.checkAllMailsInMailbox(true));
    }
}