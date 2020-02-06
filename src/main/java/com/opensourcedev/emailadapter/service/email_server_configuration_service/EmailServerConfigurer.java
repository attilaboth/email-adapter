package com.opensourcedev.emailadapter.service.email_server_configuration_service;



import javax.mail.Session;
import java.util.List;


public interface EmailServerConfigurer {

    Session connectToMailbox();
    List<String> getConnectionProperties();
}
