package main.exceptions;

public class NumberOfArgumentsException extends ArgumentsException {
    private String message;


    public NumberOfArgumentsException(String message) {
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
