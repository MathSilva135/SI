package com.devweb2.passatempo.service.exceptions;

// Esta exceção será usada quando tentarmos violar
// uma regra de integridade do banco (como excluir um pai com filhos).
public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}