package com.anthony.gestao_vagas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProfileCandidateResponseDTO {
    @Schema(example = "Candidate profile description")
    private String description;
    @Schema(example = "Javagas")
    private String username;
    @Schema(example = "javagas@email.com")
    private String email;
    @Schema(example = "+5511999999999")
    private String phone_number;
    @Schema(example = "Anthony")
    private String name;
    private UUID id;
}
