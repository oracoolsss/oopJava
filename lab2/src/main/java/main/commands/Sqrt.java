package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Sqrt implements Command {
    static Logger logger = getLogger("Logger");

    @Override
    public void execute(Data data, String str) throws ArgumentsException, NumberOfOperandsException {
        if(str != null) {
            if(str.split(" ").length != 0)
                throw new NumberOfArgumentsException("Too many arguments in SQRT");
        }

        if(data.getOperands().size() < 1) throw new NumberOfOperandsException("Not enough operands in stack for command SQRT");

        int lastInd = data.getOperands().size() - 1;

        String strArg1 = data.getOperands().get(lastInd);

        data.getOperands().remove(lastInd);

        Double arg;
        try {
            arg = data.getVars().containsKey(strArg1) ? data.getVars().get(strArg1) : Double.valueOf(strArg1);
        } catch (Exception e) {
            throw new ArgumentsException("Stack contains incorrect arguments for SQRT");
        }

        if(arg < 0.0) throw new ArgumentsException("Stack contains negative argument for SQRT");

        double result = Math.sqrt(arg);
        data.getOperands().add(String.valueOf(result));
        logger.info("SQRT's pushed result: " + result);
    }
}
