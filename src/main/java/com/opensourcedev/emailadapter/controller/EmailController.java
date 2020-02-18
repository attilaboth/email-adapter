package com.opensourcedev.emailadapter.controller;

import com.opensourcedev.emailadapter.service.email_service.EmailHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/email")
public class EmailController {

    private EmailHandler emailHandler;

    public EmailController(EmailHandler emailHandler) {
        this.emailHandler = emailHandler;
    }

    
}
