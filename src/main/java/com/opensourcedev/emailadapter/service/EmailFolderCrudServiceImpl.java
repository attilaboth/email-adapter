package com.opensourcedev.emailadapter.service;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmailFolderCrudServiceImpl implements CrudService<EmailFolderDTO, String>{

    private EmailFolderCrudService emailFolderCrudService;

    public EmailFolderCrudServiceImpl(EmailFolderCrudService emailFolderCrudService) {
        this.emailFolderCrudService = emailFolderCrudService;
    }




    @Override
    public Set<EmailFolderDTO> findAll() {
        Set<EmailFolderDTO> emailFolders = new HashSet<>();
        emailFolderCrudService.findAll().forEach(emailFolders::add);

        return emailFolders;
    }

    @Override
    public EmailFolderDTO findById(String id) {
        return emailFolderCrudService.findById(id).orElse(null);
    }

    @Override
    public EmailFolderDTO save(EmailFolderDTO emailFolder) {
        return emailFolderCrudService.save(emailFolder);
    }

    @Override
    public void deleteById(String id) {
        emailFolderCrudService.deleteById(id);
    }
}
