package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Multiplication implements Command {
    static Logger logger = getLogger("Logger");

    @Override
    public void execute(Data data, String str) throws NumberOfOperandsException, ArgumentsException {
        if(str != null) {
            if(str.split(" ").length != 0)
                throw new NumberOfArgumentsException("Too many arguments in *");
        }

        if(data.getOperands().size() < 2) throw new NumberOfOperandsException("Not enough operands in stack for command *");

        int lastInd = data.getOperands().size() - 1;

        String strArg1 = data.getOperands().get(lastInd);
        String strArg2 = data.getOperands().get(lastInd - 1);

        data.getOperands().remove(lastInd);
        data.getOperands().remove(lastInd - 1);

        Double arg1;
        Double arg2;
        try {
            arg1 = data.getVars().containsKey(strArg1) ? data.getVars().get(strArg1) : Double.valueOf(strArg1);
            arg2 = data.getVars().containsKey(strArg2) ? data.getVars().get(strArg2) : Double.valueOf(strArg2);
        } catch (Exception e) {
            throw new ArgumentsException("Stack contains incorrect arguments for *");
        }

        double result = arg1 * arg2;
        data.getOperands().add(String.valueOf(result));
        logger.info("Multiplication's pushed result: " + result);
    }
}
