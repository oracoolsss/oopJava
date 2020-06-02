package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Push implements Command {
    private static Logger logger = getLogger(Push.class.getName());

    @Override
    public void execute(Data data, String str) throws ArgumentsException {
        String[] args;
        if (str == null) {
            logger.info("NumberOfArgumentsException: Too few arguments in PUSH");
            throw new NumberOfArgumentsException("Too few arguments in PUSH");
        }

        args = str.split(" ");
        if(args.length > 1) {
            logger.info("NumberOfArgumentsException: Too many arguments in PUSH");
            throw new NumberOfArgumentsException("Too many arguments in PUSH");
        }

        if(args.length != 1) {
            logger.info("NumberOfArgumentsException: Wrong number of arguments in PUSH");
            throw new NumberOfArgumentsException("Wrong number of arguments in PUSH (" + args.length + " of " + 1);
        }
        Double newValue;
        try {
            newValue = data.containsVar(args[0]) ? data.getVar(args[0]) : Double.valueOf(args[0]);
            //newValue = data.getVars().containsKey(args[0]) ? data.getVars().get(args[0]) : Double.valueOf(args[0]);
        } catch (Exception e) {
            logger.info("ArgumentsException: Stack contains incorrect arguments for PUSH");
            throw new ArgumentsException("Stack contains incorrect arguments for PUSH");
        }
        //data.getOperands().add(newValue);
        data.pushValue(newValue);
        logger.info("New element pushed: " + args[0]);
    }
}