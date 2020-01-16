package com.opensourcedev.emailadapter.repository;

import com.opensourcedev.emailadapter.model.EmailDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<EmailDTO, String> {
}
