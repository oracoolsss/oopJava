package main.exceptions;

public class ArgumentsException extends Exception {
    private String message;
    public ArgumentsException() {
        message = "ArgumentsException";
    }

    public ArgumentsException(String message) {
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
