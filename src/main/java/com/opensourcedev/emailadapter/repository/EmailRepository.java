package com.opensourcedev.emailadapter.repository;

import com.opensourcedev.emailadapter.model.EmailDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends CrudRepository<Optional<EmailDTO>, String> {
}
