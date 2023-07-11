package it.exceptions;

public class LimiteNotificheException extends RuntimeException{

    private String message;

    public LimiteNotificheException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
