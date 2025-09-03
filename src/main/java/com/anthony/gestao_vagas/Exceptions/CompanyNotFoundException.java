package com.anthony.gestao_vagas.Exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {

        super("Company not found");
    }
}
