package com.iwa.candidats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Gère toutes les autres exceptions non spécifiées
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CandidatNotFoundException.class)
    protected ResponseEntity<Object> handleCandidatNotFound(
            CandidatNotFoundException ex) {
        // Construisez une réponse appropriée, par exemple:
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidStateException.class)
    protected ResponseEntity<Object> handleInvalidState(
            InvalidStateException ex) {
        // Construisez une réponse appropriée, par exemple:
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CandidatAlreadyExistsException.class)
    protected ResponseEntity<Object> handleCandidatAlreadyExists(
            CandidatAlreadyExistsException ex) {
        // Construisez une réponse appropriée, par exemple:
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CandidatDeletionException.class)
    protected ResponseEntity<Object> handleCandidatDeletion(
            CandidatDeletionException ex) {
        // Construisez une réponse appropriée, par exemple:
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEstablishmentDataException.class)
    public ResponseEntity<Object> handleInvalidEstablishmentDataException(InvalidEstablishmentDataException ex) {
        // Structurez le corps de la réponse comme vous le souhaitez
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<Object> handleDatabaseAccessFailure(DatabaseAccessException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Erreur de la base de données: " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }


    // You can add more handlers for other exceptions if needed
}
