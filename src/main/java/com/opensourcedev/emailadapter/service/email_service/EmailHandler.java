package com.opensourcedev.emailadapter.service.email_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class EmailHandler {

    private CheckEmail checkEmail;
    private FetchEmail fetchEmail;

    @Autowired
    public EmailHandler(CheckEmail checkEmail, FetchEmail fetchEmail) {
        this.checkEmail = checkEmail;
        this.fetchEmail = fetchEmail;
    }

    private void checkCheckEmails(boolean folderLookup){
        log.debug("[*] checkCheckEmails() method has been called with \"folderLookup\" parameter set to: " + folderLookup);
        checkEmail.checkAllMailsInMailbox(folderLookup);
    }

    private void fetchEmails(boolean folderLookup){
        log.debug("[*] fetchEmails() method has been called with \"folderLookup\" parameter set to: " + folderLookup);
        fetchEmail.fetchMailsInMailbox(folderLookup);
    }
}
