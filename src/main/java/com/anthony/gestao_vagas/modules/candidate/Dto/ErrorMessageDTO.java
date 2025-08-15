package com.anthony.gestao_vagas.modules.candidate.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    private String Message;
    private String Field;
}
