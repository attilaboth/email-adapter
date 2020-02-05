package com.opensourcedev.emailadapter.service.email_server_configuration_service;

import javax.mail.Session;
import java.util.List;

public interface MailServerConnection {

    Session connectToMailbox();
    List<String> getConnectionProperties();
}
