package main.exceptions;

public class NumberOfOperandsException extends Exception {
    private String message;

    public NumberOfOperandsException(String message) {
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
