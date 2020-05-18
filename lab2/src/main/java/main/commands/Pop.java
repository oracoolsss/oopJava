package main.commands;

import main.Data;
import main.exceptions.NumberOfArgumentsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Pop implements Command {
    private static Logger logger = getLogger("Pop");

    @Override
    public void execute(Data data, String str) throws NumberOfArgumentsException {
        if(str != null) {
            if(str.split(" ").length != 0) {
                logger.info("NumberOfArgumentsException: Too many arguments in POP");
                throw new NumberOfArgumentsException("Too many arguments in POP");
            }

        }

        if(data.getOperands().size() > 0) {
            data.getOperands().remove(data.getOperands().size() - 1);
            logger.info("Last element of stack was popped");
        }
        else {
            System.out.println("Alarm: stack was already empty before POP");
            logger.info("Attempt to pop from empty stack");
        }
    }
}
