package com.opensourcedev.emailadapter.controller;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.service.email_service.EmailHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
    public @ResponseBody List<String> checkEmailsInMailbox(@RequestBody @PathVariable @Validated boolean folderLookup){
        log.debug("[*] checkEmailsInMailbox() has been called...");
        List<String> messages = emailHandler.checkCheckEmails(folderLookup);
        log.debug("[*] List of messages contains: " + messages.toString());
        return messages;
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Set<EmailDTO> getAllEmailsInDB(){
        log.debug("[*] getAllEmailsInDB() has been called...");
        return emailHandler.findAllEmails();
    }

    @GetMapping(value = "/findById/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EmailDTO getEmailById(@RequestBody @PathVariable @Validated String emailId){
        log.debug("[*] getEmailById() has been called...");
        return emailHandler.findEmailById(emailId);
    }

    @PostMapping(value = "/storeEmails/{anySubfolders}")
    public @ResponseBody ResponseEntity storeEmailsFromFolderToDB(@RequestBody @PathVariable @Validated boolean anySubfolders){
        log.debug("[*] storeEmailsFromFolderToDB() has been called...");
        emailHandler.fetchEmails(anySubfolders);
        return ResponseEntity.ok(HttpStatus.OK + " Email has been successfully saved to database");
    }

    @PostMapping(value = "/storeEmail")
    public @ResponseBody ResponseEntity storeEmailToDB(@RequestBody @Validated EmailDTO email){
        log.debug("[*] storeEmailToDB() has been called...");
        emailHandler.storeEmailToDB(email);
        return ResponseEntity.ok(HttpStatus.OK + " Email has been successfully saved to database...");
    }

    @DeleteMapping(value = "/delete/{emailId}")
    public @ResponseBody ResponseEntity deleteEmailById(@RequestBody @PathVariable @Validated String emailId){
        log.debug("[*] deleteEmailById() has been called...");
        emailHandler.deleteById(emailId);
        return ResponseEntity.ok(HttpStatus.OK + " Email has been successfully deleted from database...");
    }


}
