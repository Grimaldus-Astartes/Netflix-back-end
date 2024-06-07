package com.netflix.store.Netflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PeliculaAlreadyExistsException.class)
    public ResponseEntity<String> handlePeliculaAlreadyExistsException(PeliculaAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + e.getMessage());
    }

    @ExceptionHandler(PeliculaNotFoundException.class)
    public ResponseEntity<String> handlePeliculaNotFoundException(PeliculaNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
    }

    @ExceptionHandler(PeliculaIncorrectDataException.class)
    public ResponseEntity<String> handlePeliculaIncorrectData(PeliculaIncorrectDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
    }
}