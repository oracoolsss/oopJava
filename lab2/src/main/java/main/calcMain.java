package main;

import main.exceptions.CalculatorException;

import java.io.*;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class calcMain {
    public static void main(String[] args) throws IOException, CalculatorException {
        Logger logger = getLogger(calcMain.class.getName());
        Calculator calculator;
        logger.info("Logging started");
        InputStream is;
        if(args.length > 0) {
            try {
                is = new FileInputStream(args[0]);
                logger.info("Started work with file");
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                is = System.in;
                logger.info("Started work with console");
            }
        }
        else {
            is = System.in;
            logger.info("Started work with console");
        }
        calculator = new Calculator(is);

        calculator.calculate();
    }
}
