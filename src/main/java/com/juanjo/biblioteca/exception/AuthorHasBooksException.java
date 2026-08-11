package com.juanjo.biblioteca.exception;

public class AuthorHasBooksException extends RuntimeException {
    public AuthorHasBooksException(Long id) {
        super("La accion de eliminar el autor con el id: " + id + " no se puede completar porque existen " +
                "otros registros que dependen de este recurso");
    }
}
