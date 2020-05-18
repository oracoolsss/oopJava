package main.exceptions;

public class NumberOfArgumentsException extends ArgumentsException {
    public NumberOfArgumentsException() {
        super("wrong number of arguments");
    }

    public NumberOfArgumentsException(String message) {
        super(message);
    }
}
