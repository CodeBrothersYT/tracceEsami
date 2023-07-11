package it.exceptions;

public class BufferVuotoException extends RuntimeException{
    private String message;
    public BufferVuotoException(String message) {
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
