package com.opensourcedev.emailadapter.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Email")
public class EmailDTO {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private Long emailId;

    @Email
    @NotNull
    @NotBlank
    private String emailAddress;

    @NotNull
    @NotBlank
    private String subject;

    @NotNull
    @NotBlank
    private String sender;

    @NotNull
    @NotBlank
    private String recipient;

    @Lob
    private String bodyAsString;

    @Lob
    private String bodyAsHtml;

    @Lob
    private Byte imageAttachment;

    @Lob
    private String textAttachment;




}
