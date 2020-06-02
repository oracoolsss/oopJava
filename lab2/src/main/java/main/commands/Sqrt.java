package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Sqrt implements Command {
    private static Logger logger = getLogger(Sqrt.class.getName());

    @Override
    public void execute(Data data, String str) throws ArgumentsException, NumberOfOperandsException {
        if(str != null) {
            if(str.split(" ").length != 0) {
                logger.info("NumberOfArgumentsException: Too many arguments in SQRT");
                throw new NumberOfArgumentsException("Too many arguments in SQRT");
            }
        }

        if(data.valuesSize() < 1) {
            logger.info("NumberOfOperandsException: Not enough operands in stack for command SQRT");
            throw new NumberOfOperandsException("Not enough operands in stack for command SQRT");
        }

        Double arg;
        try {
            arg = data.popValue();
        } catch (Exception e) {
            logger.info("ArgumentsException: Stack contains incorrect arguments for SQRT");
            throw new ArgumentsException("Stack contains incorrect arguments for SQRT");
        }

        if(arg < 0.0) {
            logger.info("ArgumentsException: Stack contains negative arguments for SQRT");
            throw new ArgumentsException("Stack contains negative argument for SQRT");
        }

        double result = Math.sqrt(arg);
        //data.getOperands().add(result);
        data.pushValue(result);
        logger.info("SQRT's pushed result: " + result);
    }
}
