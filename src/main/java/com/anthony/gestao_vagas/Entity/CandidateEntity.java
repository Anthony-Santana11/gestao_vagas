package com.anthony.gestao_vagas.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
import org.springframework.data.domain.Example;

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
    @Schema(example = "anthony123" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Pattern(regexp = "(^[A-Z][a-zA-Z\\s]*$)", message = "Nome deve começar com letra maiúscula e conter apenas letras e espaços")
    @Schema(example = "Anthony" , requiredMode = Schema.RequiredMode.REQUIRED , description = "Candidate name, must start with a capital letter and contain only letters and spaces.")
    private String name;

    @Email(message = "O campo [email] deve ter um formato válido")
    @Schema(example = "Anthony@gmail.com" , requiredMode = Schema.RequiredMode.REQUIRED, description = "Candidate email address, must be a valid email format.")
    private String email;

    @Length(min = 11, max = 11, message = "O campo [phoneNumber] deve ter exatamente 11 dígitos")
    @Pattern(regexp = "^[0-9]+$", message = "O campo [phoneNumber]  deve conter apenas numeros")
    @Schema(example = "+5511999999999" , requiredMode = Schema.RequiredMode.REQUIRED , description = "Candidate phone number")
    private String phoneNumber;

    @Length(min = 7 , max = 200, message = "o campo [password] Senha deve ter entre 7 e 20 caracteres")
    @Schema(example = "senha123" , requiredMode = Schema.RequiredMode.REQUIRED, description = "Candidate password, must be between 7 and 20 characters long.")
    private String password;

    @Schema(example = "Candidate profile description", description = "A brief description of the candidate's profile, highlighting skills and experiences.")
    @Length(max = 500, message = "O campo [description] deve ter no máximo 500 caracteres")
    private String description;

    @Schema(example = "Java Developer")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
