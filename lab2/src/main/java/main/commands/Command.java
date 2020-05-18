package main.commands;

import main.Data;
import main.exceptions.CalculatorException;

public interface Command {
    public void execute(Data data, String args) throws CalculatorException;
}