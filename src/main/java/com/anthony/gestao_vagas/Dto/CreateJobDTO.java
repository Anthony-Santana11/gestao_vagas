package com.anthony.gestao_vagas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {
    @Schema (example = "Vaga para Desenvolvedor Java Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema (example = "Gym Pass , Plano de saúde , Vale Refeição", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema (example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
