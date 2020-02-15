package com.opensourcedev.emailadapter.service.crud_service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.repository.EmailRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmailCrudServiceImpl implements CrudService<Optional<EmailDTO>, String>{


    private final EmailRepository emailRepository;

    public EmailCrudServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }



    @Override
    @Transactional(readOnly = true)
    public Set<Optional<EmailDTO>> findAll() {
        Set<Optional<EmailDTO>> emails = new HashSet<>();
        emailRepository.findAll().forEach(emails::add);

        return emails;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmailDTO> findById(String id) {
        return emailRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<EmailDTO> save(Optional<EmailDTO> email) {
        emailRepository.save(email).orElseThrow(PersistenceException::new);
        return email;
    }

    @Override
    public void deleteById(String id) {
        emailRepository.deleteById(id);
    }

}
