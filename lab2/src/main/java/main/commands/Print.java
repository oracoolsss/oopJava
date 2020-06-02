package main.commands;

import main.Data;
import main.exceptions.NumberOfArgumentsException;
import main.exceptions.NumberOfOperandsException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Print implements Command{
    private static Logger logger = getLogger(Print.class.getName());

    @Override
    public void execute(Data data,String str) throws NumberOfArgumentsException {
        if(str != null) {
            if(str.split(" ").length != 0) {
                logger.info("NumberOfArgumentsException: Too many arguments in PRINT");
                throw new NumberOfArgumentsException("Too many arguments in PRINT");
            }
        }

        //System.out.println(data.getOperands().get(data.getOperands().size() - 1));
        System.out.println(data.peekValue());
        logger.info("Printed value: " + data.getOperands().get(data.getOperands().size() - 1));
    }
}
