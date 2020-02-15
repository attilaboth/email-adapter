package com.opensourcedev.emailadapter.service.crud_service;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import com.opensourcedev.emailadapter.repository.EmailFolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmailFolderCrudServiceImpl implements CrudService<Optional<EmailFolderDTO>, String>{

    private EmailFolderRepository emailFolderRepository;

    public EmailFolderCrudServiceImpl(EmailFolderRepository emailFolderRepository) {
        this.emailFolderRepository = emailFolderRepository;
    }




    @Override
    @Transactional(readOnly = true)
    public Set<Optional<EmailFolderDTO>> findAll() {
        Set<Optional<EmailFolderDTO>> emailFolders = new HashSet<>();
        emailFolderRepository.findAll().forEach(emailFolders::add);

        return emailFolders;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmailFolderDTO> findById(String id) {
        return emailFolderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<EmailFolderDTO> save(Optional<EmailFolderDTO> emailFolder) {
        emailFolderRepository.save(emailFolder).orElseThrow(PersistenceException::new);
        return emailFolder;    }

    @Override
    public void deleteById(String id) {
        emailFolderRepository.deleteById(id);
    }



}
