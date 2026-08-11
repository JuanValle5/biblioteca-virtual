package com.juanjo.biblioteca.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
        super("No existe un autor con id " + id);
    }

}