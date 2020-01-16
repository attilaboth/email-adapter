package com.opensourcedev.emailadapter.service;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailFolderCrudService extends CrudRepository<EmailFolderDTO, String> {
}
