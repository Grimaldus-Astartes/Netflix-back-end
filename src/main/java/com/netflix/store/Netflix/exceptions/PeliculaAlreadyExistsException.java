package com.netflix.store.Netflix.exceptions;

public class PeliculaAlreadyExistsException extends RuntimeException {
    public PeliculaAlreadyExistsException(String message) {
        super(message);
    }

}
