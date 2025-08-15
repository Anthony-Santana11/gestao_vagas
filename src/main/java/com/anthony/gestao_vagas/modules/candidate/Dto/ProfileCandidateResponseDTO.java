package com.anthony.gestao_vagas.modules.candidate.Dto;

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
    private String description;
    private String username;
    private String email;
    private String phone_number;
    private String name;
    private UUID id;
}
