package com.anthony.gestao_vagas.modules.candidate.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

    private String email;
    private String password;
    private String username;

}
