package com.opensourcedev.emailadapter.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "EmailFolder")
public class EmailFolderDTO {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "folder_id")
    private String folderId;

    @NotNull
    @NotBlank
    @Column(name = "folder_name")
    private String folderName;

    @Column(name = "root_folder")
    private Boolean rootFolder;

    @Min(0)
    @PositiveOrZero
    @Column(name = "number_of_sub_folders")
    private Integer numberOfSubFolders;


}
