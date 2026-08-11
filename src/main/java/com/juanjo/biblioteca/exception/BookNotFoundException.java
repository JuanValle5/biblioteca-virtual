package com.juanjo.biblioteca.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("No existe el libro con el id " + id);
    }
}
