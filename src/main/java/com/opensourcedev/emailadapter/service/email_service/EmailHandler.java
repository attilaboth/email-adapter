package com.opensourcedev.emailadapter.service.email_service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<String> checkCheckEmails(boolean folderLookup){
        log.debug("[*] checkCheckEmails() method has been called with \"folderLookup\" parameter set to: " + folderLookup);
        List<String> messages = checkEmail.checkAllMailsInMailbox(folderLookup);
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

    public Set<EmailDTO> findAllEmails(){
        log.debug("[*] findAllEmails() method has been called...");
        Set<EmailDTO> emails = new HashSet<>();
        emailRepository.findAll().forEach(emails::add);
        return emails;
    }

    public EmailDTO findEmailById(String id){
        log.debug("[*] findEmailById() method has been called from EmailHandler ...");
        if(emailRepository.findById(id).isPresent()){
            return emailRepository.findById(id).get();
        }else {
            try {
                 throw new Exception("[!!] emailRepository.findById(id).get() returned Null...");
            }catch (Exception e){
                log.debug("[!!] Error throwing exception in findEmailById()...");
                e.getMessage();
                e.printStackTrace();
                return new EmailDTO();
            }
        }
    }

    public void deleteById(String id){
        log.debug("[*] deleteById() method has been called from EmailHandler" +
                " with id: " + id);
        emailRepository.deleteById(id);
    }

}
