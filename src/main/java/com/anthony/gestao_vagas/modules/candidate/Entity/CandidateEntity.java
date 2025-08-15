package com.anthony.gestao_vagas.modules.candidate.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "Candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços em branco")
    @Length(min = 3, max = 20, message = "O campo [username] deve ter entre 3 e 20 caracteres")
    private String username;

    @Pattern(regexp = "(^[A-Z][a-zA-Z\\s]*$)", message = "Nome deve começar com letra maiúscula e conter apenas letras e espaços")
    private String name;

    @Email(message = "O campo [email] deve ter um formato válido")
    private String email;

    @Length(min = 11, max = 11, message = "O campo [phoneNumber] deve ter exatamente 11 dígitos")
    @Pattern(regexp = "^[0-9]+$", message = "O campo [phoneNumber]  deve conter apenas numeros")
    private String phoneNumber;

    @Length(min = 7 , max = 200, message = "o campo [password] Senha deve ter entre 7 e 20 caracteres")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
