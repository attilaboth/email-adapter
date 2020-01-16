package com.opensourcedev.emailadapter.repository;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailFolderRepository extends CrudRepository<EmailFolderDTO, String> {
}
