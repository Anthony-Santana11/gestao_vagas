package com.anthony.gestao_vagas.modules.candidate.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthCandidateResponseDTO {
    private String acess_token;
    private Long expires_in;
}
