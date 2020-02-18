package com.opensourcedev.emailadapter.service.crud_service;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import com.opensourcedev.emailadapter.repository.EmailFolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmailFolderCrudServiceImpl implements CrudService<EmailFolderDTO, String>{

    private EmailFolderRepository emailFolderRepository;

    public EmailFolderCrudServiceImpl(EmailFolderRepository emailFolderRepository) {
        this.emailFolderRepository = emailFolderRepository;
    }




    @Override
    @Transactional(readOnly = true)
    public Set<EmailFolderDTO> findAll() {
        Set<EmailFolderDTO> emailFolders = new HashSet<>();
        emailFolderRepository.findAll().forEach(emailFolders::add);

        return emailFolders;
    }

    @Override
    @Transactional(readOnly = true)
    public EmailFolderDTO findById(String id) {
        return emailFolderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public EmailFolderDTO save(EmailFolderDTO emailFolder) {
        emailFolderRepository.save(emailFolder);
        return emailFolder;
    }

    @Override
    public void deleteById(String id) {
        emailFolderRepository.deleteById(id);
    }



}
