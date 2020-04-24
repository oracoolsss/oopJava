package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfArgumentsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Define implements Command {
    static Logger logger = getLogger("Logger");

    @Override
    public void execute(Data data, String str) throws ArgumentsException {
        String[] args;
        Double constValue;
        if(str == null) {
            throw new NumberOfArgumentsException("Too few arguments in DEFINE");
        }
        args = str.split(" ");
        if(args.length > 2) throw new NumberOfArgumentsException("Too many arguments in PUSH");
        try {
            constValue = Double.valueOf(args[1]);
        } catch (Exception e) {
            throw new ArgumentsException("Incorrect argument for definition");
        }
        data.getVars().put(args[0], constValue);
        logger.info("New constant pushed: " + args[0] + " = " + constValue);
    }
}
