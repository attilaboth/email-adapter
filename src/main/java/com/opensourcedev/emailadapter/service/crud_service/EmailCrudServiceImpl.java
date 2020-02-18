package com.opensourcedev.emailadapter.service.crud_service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.repository.EmailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Service
public class EmailCrudServiceImpl implements CrudService<EmailDTO, String>{


    private final EmailRepository emailRepository;

    public EmailCrudServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }



    @Override
    public EmailDTO save(EmailDTO email) {
        emailRepository.save(email);
        return email;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<EmailDTO> findAll() {
        Set<EmailDTO> emails = new HashSet<>();
        emailRepository.findAll().forEach(emails::add);
        return emails;
    }

    @Override
    @Transactional(readOnly = true)
    public EmailDTO findById(String id) {

       return emailRepository.findById(id).orElseThrow();
    }


    @Override
    public void deleteById(String id) {
        emailRepository.deleteById(id);
    }

}
