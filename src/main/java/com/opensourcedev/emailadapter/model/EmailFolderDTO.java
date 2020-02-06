package com.opensourcedev.emailadapter.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EmailFolder")
public class EmailFolderDTO {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String folderId;

    @NotNull
    @NotBlank
    private String folderName;
    private Boolean rootFolder;

    @Min(0)
    @PositiveOrZero
    private Integer numberOfSubFolders;


}
