package com.opensourcedev.emailadapter.service.email_server_configuration_service;



import javax.mail.Store;
import java.util.List;


public interface EmailServerConfigurer {

    Store connectToMailbox();
    List<String> getConnectionProperties();
}
