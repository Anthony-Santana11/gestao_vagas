package com.anthony.gestao_vagas.modules.candidate.Dto;

import lombok.Data;

@Data
public class CreateJobDTO {

    private String description;
    private String benefits;
    private String level;
}
