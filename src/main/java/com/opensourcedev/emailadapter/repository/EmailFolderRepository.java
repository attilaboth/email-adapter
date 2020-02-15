package com.opensourcedev.emailadapter.repository;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailFolderRepository extends CrudRepository<Optional<EmailFolderDTO>, String> {
}
