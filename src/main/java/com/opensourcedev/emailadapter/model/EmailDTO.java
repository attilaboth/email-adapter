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
    private String emailId;

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
    private List<Byte> imageAttachment;

    @Lob
    private List<String> textAttachment;




}
