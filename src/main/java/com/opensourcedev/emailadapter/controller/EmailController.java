package com.opensourcedev.emailadapter.controller;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.service.email_service.EmailHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;

@RestController
@Slf4j
@RequestMapping({"/email", "/Email"})
public class EmailController {

    private final EmailHandler emailHandler;

    @Autowired
    public EmailController(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }


    @GetMapping(value = "/checkMailbox/{folderLookup}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message[] checkEmailsInMailbox(@RequestBody @PathVariable boolean folderLookup){
        log.debug("[*] checkEmailsInMailbox() has been called...");
        Message[] messages = emailHandler.checkCheckEmails(folderLookup);
        log.debug("[*] List of messages contains: " + messages.toString());
        return messages;
    }

    @PostMapping(value = "/storeEmails/{anySubfolders}")
    public @ResponseBody ResponseEntity storeEmailsFromFolderToDB(@RequestBody @PathVariable boolean anySubfolders){
        log.debug("[*] storeEmailsFromFolderToDB() has been called...");
        emailHandler.fetchEmails(anySubfolders);
        return ResponseEntity.ok(HttpStatus.OK + " Email has been successfully saved to database");
    }

    @PostMapping(value = "/storeEmail")
    public @ResponseBody ResponseEntity storeEmailToDB(@RequestBody EmailDTO email){
        log.debug("[*] storeEmailToDB() has been called...");
        emailHandler.storeEmailToDB(email);
        return ResponseEntity.ok(HttpStatus.OK + " Email has been successfully saved to database...");
    }


}
