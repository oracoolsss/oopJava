package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;
import main.exceptions.DivisionByZeroException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Division implements Command{
    private static Logger logger = getLogger("Division");

    @Override
    public void execute(Data data, String str) throws NumberOfOperandsException, ArgumentsException, DivisionByZeroException {
        if(str != null) {
            if(str.split(" ").length != 0) {
                logger.info("NumberOfArgumentsException: Too many arguments in /");
                throw new NumberOfArgumentsException("Too many arguments in /");
            }
        }

        if(data.getOperands().size() < 2) {
            logger.info("NumberOfParamsException: Not enough operands in stack for command /");
            throw new NumberOfOperandsException("Not enough operands in stack for command /");
        }

        Double arg1;
        Double arg2;
        try {
            arg2 = data.popValue();
            arg1 = data.popValue();
        } catch (Exception e) {
            logger.info("ArgumentsException: Stack contains incorrect arguments for /");
            throw new ArgumentsException("Stack contains incorrect arguments for /");
        }

        if(arg2 == 0.0) {
            logger.info("DivisionByZeroException: Division by 0 happened");
            throw new DivisionByZeroException("Division by 0 happened");
        }

        double result = arg1 / arg2;
        data.getOperands().add(result);
        logger.info("Division's pushed result: " + result);
    }
}
