package com.opensourcedev.emailadapter.service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmailCrudServiceImpl implements CrudService<EmailDTO, String>{


    private final EmailCrudService emailCrudService;

    public EmailCrudServiceImpl(EmailCrudService emailCrudService) {
        this.emailCrudService = emailCrudService;
    }





    @Override
    public Set<EmailDTO> findAll() {
        Set<EmailDTO> emails = new HashSet<>();

        emailCrudService.findAll().forEach(emails::add);
        return emails;
    }

    @Override
    public EmailDTO findById(String id) {

        return emailCrudService.findById(id).orElse(null);
    }

    @Override
    public EmailDTO save(EmailDTO email) {
        return emailCrudService.save(email);
    }

    @Override
    public void deleteById(String id) {
        emailCrudService.deleteById(id);
    }
}
