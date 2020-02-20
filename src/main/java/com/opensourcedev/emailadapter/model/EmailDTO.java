package com.opensourcedev.emailadapter.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

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
    @Column(name = "email_id")
    private String emailId;


    @NotNull
    @NotBlank
    @Column(name = "subject")
    private String subject;

    @Email
    @NotNull
    @NotBlank
    @Column(name = "sender")
    private String sender;

    @Email
    @NotNull
    @NotBlank
    @Column(name = "recipient")
    private String recipient;

    @Lob
    @Column(name = "body_as_string")
    private String bodyAsString;

    @Lob
    @Column(name = "image_attachment")
    private byte[] imageAttachment;

    @Lob
    @Column(name = "text_attachment")
    private byte[] textAttachment;




}
