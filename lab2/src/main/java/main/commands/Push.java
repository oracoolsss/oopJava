package main.commands;

import main.Data;
import main.exceptions.NumberOfArgumentsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Push implements Command {
    static Logger logger = getLogger("Logger");

    @Override
    public void execute(Data data, String str) throws NumberOfArgumentsException {
        String[] args;
        if (str == null) throw new NumberOfArgumentsException("Too few arguments in PUSH");

        args = str.split(" ");
        if(args.length > 1) throw new NumberOfArgumentsException("Too many arguments in PUSH");

        if(args.length != 1) {
            throw new NumberOfArgumentsException("Wrong number of arguments in PUSH (" + args.length + " of " + 1);
        }

        data.getOperands().add(args[0]);
        logger.info("New element pushed: " + args[0]);
    }
}