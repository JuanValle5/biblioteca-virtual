package com.juanjo.biblioteca.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException ex) {
        String detail = ex.getMostSpecificCause().getMessage();
        if (detail.contains("idx_book_title")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un libro con ese título y autor");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                "No se puede completar la operación porque otros registros dependen de este recurso"
        );
    }

    @ExceptionHandler(AuthorHasBooksException.class)
    public ResponseEntity<String> dataIntegrity(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({AuthorNotFoundException.class,BookNotFoundException.class})
    public ResponseEntity<String> notFound(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
