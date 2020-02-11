package com.opensourcedev.emailadapter.service;

import com.opensourcedev.emailadapter.model.EmailDTO;
import com.opensourcedev.emailadapter.repository.EmailRepository;
import com.opensourcedev.emailadapter.service.crud_service.EmailCrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;



@ExtendWith(MockitoExtension.class)
class EmailCrudServiceImplTest {

    @Mock
    EmailRepository emailRepository;

    @InjectMocks
    EmailCrudServiceImpl emailCrudServiceImpl;

    EmailDTO testEmail;

    @BeforeEach
    void setUp() {
        testEmail = EmailDTO.builder().emailId("cab35c05-681f-44dc-a34d-1b3335873c5a")
                .emailAddress("example@gmail.com")
                .subject("testSubject")
                .sender("Rashid")
                .recipient("Kumar")
                .build();
    }



    @Test
    void findAll() {
        Set<EmailDTO> emails = new HashSet<>();
        Set<EmailDTO> mails;

        emails.add(EmailDTO.builder().emailId("3d7df87e-8325-46f9-87cd-5a91460f8a26").build());
        emails.add(EmailDTO.builder().emailId("ab758c42-d86b-49d2-9ca3-8e0f6a651921").build());

        when(emailRepository.findAll()).thenReturn(emails);

        mails = emailCrudServiceImpl.findAll();

        assertNotNull(mails);
        assertEquals(2, mails.size());
    }

    @Test
    void findById() {
        when(emailRepository.findById(anyString())).thenReturn(Optional.of(testEmail));
        EmailDTO emailDTO = emailCrudServiceImpl.findById("def651ee-cdc0-403e-b052-1737c268dbf7");

        assertNotNull(emailDTO);
    }

    @Test
    void findByIdNotFound(){
        when(emailRepository.findById(anyString())).thenReturn(Optional.empty());
        EmailDTO emailDTO = emailCrudServiceImpl.findById("def651ee-cdc0-403e-b052-1737c268dbf7");

        assertNull(emailDTO);
    }

    @Test
    void save() {
        EmailDTO emailToSave = EmailDTO.builder().emailId("def651ee-cdc0-403e-b052-1737c268dbf7").build();
        when(emailRepository.save(any())).thenReturn(emailToSave);

        EmailDTO savedEmail = emailCrudServiceImpl.save(emailToSave);

        assertNotNull(savedEmail);
        verify(emailRepository).save(any());
    }

    @Test
    void deleteById() {
        emailRepository.deleteById("3d7df87e-8325-46f9-87cd-5a91460f8a26");
        verify(emailRepository, times(1)).deleteById(anyString());
    }
}