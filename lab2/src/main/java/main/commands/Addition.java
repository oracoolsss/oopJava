package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;
import main.exceptions.CalculatorException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Addition implements Command {
    private static Logger logger = getLogger(Addition.class.getName());

    @Override
    public void execute(Data data, String str) throws CalculatorException {
        if(str != null) {
            if(str.split(" ").length != 0)
            throw new NumberOfArgumentsException("Too many arguments in +");
        }

        if(data.valuesSize() < 2) throw new NumberOfOperandsException("Not enough operands in stack for command +");

        Double arg1;
        Double arg2;
        try {
            arg1 = data.popValue();
            arg2 = data.popValue();
        } catch (Exception e) {
            logger.info("ArgumentsException: Stack contains incorrect arguments for +");
            throw new ArgumentsException("Stack contains incorrect arguments for +");
        }

        double result = arg1 + arg2;
        data.pushValue(result);
        logger.info("Addition's pushed result: " + result);
    }
}
