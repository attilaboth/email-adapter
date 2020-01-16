package com.opensourcedev.emailadapter.service;

import com.opensourcedev.emailadapter.model.EmailFolderDTO;
import com.opensourcedev.emailadapter.repository.EmailFolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailFolderCrudServiceImplTest {

    @Mock
    EmailFolderRepository emailFolderRepository;

    @InjectMocks
    EmailFolderCrudServiceImpl emailFolderCrudServiceImpl;

    EmailFolderDTO testEmailFolder;


    @BeforeEach
    void setUp() {
        testEmailFolder = EmailFolderDTO.builder().folderId("def651ee-cdc0-403e-b052-1737c268dbf7")
                .folderName("INBOX")
                .rootFolder(true)
                .numberOfSubFolder(5)
                .build();
    }




    @Test
    void findAll() {
        Set<EmailFolderDTO> emailFolders = new HashSet<>();
        Set<EmailFolderDTO> mailFolders;

        emailFolders.add(EmailFolderDTO.builder().folderId("3d7df87e-8325-46f9-87cd-5a91460f8a26").build());
        emailFolders.add(EmailFolderDTO.builder().folderId("ab758c42-d86b-49d2-9ca3-8e0f6a651921").build());

        when(emailFolderRepository.findAll()).thenReturn(emailFolders);

        mailFolders = emailFolderCrudServiceImpl.findAll();

        assertNotNull(mailFolders);
        assertEquals(2, mailFolders.size());

    }

    @Test
    void findById() {
        when(emailFolderRepository.findById(anyString())).thenReturn(Optional.of(testEmailFolder));
        EmailFolderDTO emailFolderDTO = emailFolderCrudServiceImpl.findById("def651ee-cdc0-403e-b052-1737c268dbf7");

        assertNotNull(emailFolderDTO);
    }

    @Test
    void findByIdNotFound(){
        when(emailFolderRepository.findById(anyString())).thenReturn(Optional.empty());
        EmailFolderDTO emailFolderDTO = emailFolderCrudServiceImpl.findById("def651ee-cdc0-403e-b052-1737c268dbf7");

        assertNull(emailFolderDTO);
    }

    @Test
    void save() {
        EmailFolderDTO folderToSave = EmailFolderDTO.builder().folderId("def651ee-cdc0-403e-b052-1737c268dbf7").build();
        when(emailFolderRepository.save(any())).thenReturn(folderToSave);

        EmailFolderDTO savedEmailFolder = emailFolderCrudServiceImpl.save(folderToSave);
        assertNotNull(savedEmailFolder);
        verify(emailFolderRepository).save(any());
    }

    @Test
    void deleteById() {
        emailFolderCrudServiceImpl.deleteById("3d7df87e-8325-46f9-87cd-5a91460f8a26");
        verify(emailFolderRepository, times(1)).deleteById(anyString());

    }
}