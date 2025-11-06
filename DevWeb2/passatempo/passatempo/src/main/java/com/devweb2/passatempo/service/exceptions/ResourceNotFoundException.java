package com.devweb2.passatempo.service.exceptions;

// Esta é a nossa exceção personalizada.
// Ela será "lançada" pelo Service sempre que um recurso não for encontrado.
// A anotação @ResponseStatus é um atalho para dizer ao Spring que esta exceção
// deve, por padrão, gerar uma resposta HTTP 404 Not Found.
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
