package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;
import main.exceptions.DivisionByZeroException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Division implements Command{
    static Logger logger = getLogger("Logger");

    @Override
    public void execute(Data data, String str) throws NumberOfOperandsException, ArgumentsException, DivisionByZeroException {
        if(str != null) {
            if(str.split(" ").length != 0)
                throw new NumberOfArgumentsException("Too many arguments in /");
        }

        if(data.getOperands().size() < 2) throw new NumberOfOperandsException("Not enough operands in stack for command *");

        int lastInd = data.getOperands().size() - 1;

        String strArg1 = data.getOperands().get(lastInd - 1);
        String strArg2 = data.getOperands().get(lastInd);

        data.getOperands().remove(lastInd);
        data.getOperands().remove(lastInd - 1);

        Double arg1;
        Double arg2;
        try {
            arg1 = data.getVars().containsKey(strArg1) ? data.getVars().get(strArg1) : Double.valueOf(strArg1);
            arg2 = data.getVars().containsKey(strArg2) ? data.getVars().get(strArg2) : Double.valueOf(strArg2);
        } catch (Exception e) {
            throw new ArgumentsException("Stack contains incorrect arguments for /");
        }

        if(arg2 == 0.0) throw new DivisionByZeroException("Division by 0 happened");

        double result = arg1 / arg2;
        data.getOperands().add(String.valueOf(result));
        logger.info("Division's pushed result: " + result);
    }
}