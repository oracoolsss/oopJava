package main;

import main.exceptions.ArgumentsException;
import main.exceptions.DivisionByZeroException;
import main.exceptions.NumberOfOperandsException;

import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class calcMain {
    public static void main(String[] args) throws NumberOfOperandsException, ArgumentsException, IOException, DivisionByZeroException {
        Logger logger = getLogger("Logger");
        Calculator calculator;
        logger.info("Logging started");
        try {
            calculator = new Calculator(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            calculator = new Calculator("");
        }

        calculator.calculate();
    }
}
