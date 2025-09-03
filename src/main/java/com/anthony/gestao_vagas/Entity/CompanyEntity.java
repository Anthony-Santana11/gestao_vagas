package com.anthony.gestao_vagas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue ( strategy =  GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Length(min = 3, max = 20, message = "O campo [username] deve ter entre 3 e 20 caracteres")
    private String username;

    @Email(message = "O campo [email] deve ter um formato válido")
    private String email;

    @Length (min = 8, max = 200, message = "O campo [password] deve ter entre 8 e 20 caracteres")
    @NotBlank(message = "O campo [password] é obrigatório")
    private String password;


    private String website;

    @NotBlank
    private String  name;

    @NotBlank
    @Length( min = 30, max = 500, message = "A descrição deve ter entre 100 e 500 caracteres")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
