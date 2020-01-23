package com.opensourcedev.emailadapter.service.crud_service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmailCrudServiceImpl implements CrudService<EmailDTO, String>{


    private final EmailRepository emailRepository;

    public EmailCrudServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }





    @Override
    public Set<EmailDTO> findAll() {
        Set<EmailDTO> emails = new HashSet<>();

        emailRepository.findAll().forEach(emails::add);
        return emails;
    }

    @Override
    public EmailDTO findById(String id) {

        return emailRepository.findById(id).orElse(null);
    }

    @Override
    public EmailDTO save(EmailDTO email) {
        return emailRepository.save(email);
    }

    @Override
    public void deleteById(String id) {
        emailRepository.deleteById(id);
    }
}
