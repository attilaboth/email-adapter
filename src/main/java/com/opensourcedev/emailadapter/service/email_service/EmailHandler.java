package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.mail.Message;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class EmailHandler {

    private CheckEmail checkEmail;
    private FetchEmail fetchEmail;
    private EmailDTO emailDTO;
    private EmailRepository emailRepository;

    @Autowired
    public EmailHandler(CheckEmail checkEmail, FetchEmail fetchEmail, EmailDTO emailDTO, EmailRepository emailRepository) {
        this.checkEmail = checkEmail;
        this.fetchEmail = fetchEmail;
        this.emailDTO = emailDTO;
        this.emailRepository = emailRepository;
    }

    public Message[] checkCheckEmails(boolean folderLookup){
        log.debug("[*] checkCheckEmails() method has been called with \"folderLookup\" parameter set to: " + folderLookup);
        Message[] messages = checkEmail.checkAllMailsInMailbox(folderLookup);
        return messages;
    }

    public void fetchEmails(boolean folderLookup){
        log.debug("[*] fetchEmails() method has been called with \"folderLookup\" parameter set to: " + folderLookup);
        fetchEmail.fetchMailsInMailbox(folderLookup);
    }

    public void storeEmailToDB(EmailDTO email){
        log.debug("[*] storeEmailToDB() method has been called...");
        emailRepository.save(email);
    }
}
