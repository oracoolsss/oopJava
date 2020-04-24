package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.DivisionByZeroException;
import main.exceptions.NumberOfOperandsException;

public interface Command {
    public void execute(Data data, String args) throws NumberOfOperandsException, ArgumentsException, DivisionByZeroException;
}
