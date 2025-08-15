package com.anthony.gestao_vagas.modules.candidate.Exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já existe");
    }
}
