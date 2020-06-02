package main;

import main.commands.Command;
import main.commands.CommandFactory;
import main.exceptions.CalculatorException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Calculator {
    private Data data = new Data();
    private Scanner scanner;
    private static Logger logger = getLogger(Calculator.class.getName());

    public Calculator(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    void calculate() throws IOException, CalculatorException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String str;
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();

            if(str.length() < 1 || str.charAt(0) == '#') {
                continue;
            }

            String[] lineElements = str.split(" ", 2);

            Command command = commandFactory.createCommand(lineElements[0]);
            if(command != null) {
                command.execute(data, lineElements.length > 1 ? lineElements[1] : null);
            }
            else {
                System.out.println("wrong operation name");
                logger.info("wrong operation name");
            }
        }

        logger.info("Calculated successfully");
    }
}
