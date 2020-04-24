package main.exceptions;

public class DivisionByZeroException extends Exception {
    private String message;

    public DivisionByZeroException(String message) {
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
