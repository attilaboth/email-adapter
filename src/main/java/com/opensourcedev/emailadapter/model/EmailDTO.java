package com.opensourcedev.emailadapter.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import java.util.List;

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

    @Email
    @NotNull
    @NotBlank
    @Column(name = "email_address")
    private String emailAddress;

    @NotNull
    @NotBlank
    @Column(name = "subject")
    private String subject;

    @NotNull
    @NotBlank
    @Column(name = "sender")
    private String sender;

    @NotNull
    @NotBlank
    @Column(name = "recipient")
    private String recipient;

    @Lob
    @Column(name = "body_as_string")
    private String bodyAsString;

    @Lob
    @Column(name = "body_as_html")
    private String bodyAsHtml;

    @Lob
    @Column(name = "image_attachment")
    private List<Byte> imageAttachment;

    @Lob
    @Column(name = "text_attachment")
    private List<String> textAttachment;




}
