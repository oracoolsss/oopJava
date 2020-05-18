package main.exceptions;

public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException() {
        super("division by zero");
    }

    public DivisionByZeroException(String message) {
        super(message);
    }
}
