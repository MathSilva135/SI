package com.devweb2.passatempo.resources.exceptions;

import com.devweb2.passatempo.dto.ErrorResponseDTO;


import com.devweb2.passatempo.service.exceptions.DataIntegrityException;
import com.devweb2.passatempo.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// Esta classe é o "Gerente de Exceções" central.
// A anotação @ControllerAdvice faz com que ela "observe" todos os Controllers.
@ControllerAdvice
public class RestExceptionHandler {

    // Este método é um "ouvinte" de alarmes.
    // @ExceptionHandler diz: "Se qualquer Controller lançar uma ResourceNotFoundException,
    // execute este método em vez de quebrar a aplicação."
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        // 1. Cria o DTO de erro
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );

        // 2. Retorna o ResponseEntity formatado com o DTO de erro
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErrorResponseDTO> dataIntegrityViolation(DataIntegrityException ex, HttpServletRequest request) {

        // Usamos 400 BAD_REQUEST (ou 409 CONFLICT)
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(), // A mensagem amigável que definimos no Service
                status.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(errorResponse);
    }

}