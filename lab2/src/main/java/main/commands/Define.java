package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Define implements Command {
    private static Logger logger = getLogger(Define.class.getName());

    @Override
    public void execute(Data data, String str) throws ArgumentsException {
        String[] args;
        Double constValue;
        if(str == null) {
            logger.info("NumberOfArgumentsException: Too few arguments in DEFINE");
            throw new NumberOfArgumentsException("Too few arguments in DEFINE");
        }
        args = str.split(" ");
        if(args.length != 2) throw new NumberOfArgumentsException("Wrong number of arguments in DEFINE");
        try {
            constValue = Double.valueOf(args[1]);
        } catch (Exception e) {
            logger.info("ArgumentsException: Incorrect argument for definition");
            throw new ArgumentsException("Incorrect argument for definition");
        }
        //data.getVars().put(args[0], constValue);
        data.setVar(args[0], constValue);
        logger.info("New constant pushed: " + args[0] + " = " + constValue);
    }
}
