package main;

import main.commands.Command;
import main.commands.CommandFactory;
import main.exceptions.ArgumentsException;
import main.exceptions.DivisionByZeroException;
import main.exceptions.NumberOfOperandsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Calculator {
    private Data data = new Data();
    private Scanner scanner;
    static Logger logger = getLogger("Logger");

    public Calculator(String fileName) {
        try {
            scanner = new Scanner(new File(fileName));
            logger.info("Started work with file");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            scanner = new Scanner(System.in);
            logger.info("Started work with console");
        }
    }

    void calculate() throws ArgumentsException, NumberOfOperandsException, IOException, DivisionByZeroException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String str;
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if(str.charAt(0) == '#') {
                continue;
            }
            String[] lineElements = str.split(" ", 2);
            Command command = commandFactory.createCommand(lineElements[0]);
            command.execute(data, lineElements.length > 1 ? lineElements[1] : null);
        }

        logger.info("Calculated successfully");
    }
}
