package main.exceptions;

public class NumberOfOperandsException extends CalculatorException {
    public NumberOfOperandsException() {
        super("wrong number of operands");
    }

    public NumberOfOperandsException(String message) {
        super(message);
    }
}
