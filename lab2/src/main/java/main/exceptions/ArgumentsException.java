package main.exceptions;

public class ArgumentsException extends CalculatorException {
    public ArgumentsException() {
        super("ArgumentsException");
    }

    public ArgumentsException(String message) {
        super(message);
    }
}
