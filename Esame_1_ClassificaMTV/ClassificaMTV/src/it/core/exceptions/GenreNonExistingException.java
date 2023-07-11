package it.core.exceptions;

public class GenreNonExistingException extends RuntimeException {
    private String message;
    public GenreNonExistingException(String message){
        this.message = message;
    }
}
