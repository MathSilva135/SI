package com.devweb2.passatempo.dto;

import java.time.LocalDateTime;

// Este é o DTO que será enviado ao frontend quando um erro acontecer.
// Ele formata o erro de um jeito amigável para o frontend consumir (em JSON).
public class ErrorResponseDTO {
    private String message;
    private LocalDateTime timestamp;
    private Integer status;
    private String path; // Opcional, mas bom ter

    // Construtores
    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDTO(String message, Integer status, String path) {
        this();
        this.message = message;
        this.status = status;
        this.path = path;
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
