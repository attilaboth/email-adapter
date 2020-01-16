package com.opensourcedev.emailadapter.service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCrudService extends CrudRepository<EmailDTO, String> {
}
