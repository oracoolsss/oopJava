package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfOperandsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest {
    @Test
    void successExecute() throws ArgumentsException, NumberOfOperandsException {
        Multiplication operation = new Multiplication();
        Data data = new Data();
        data.getOperands().add("1");
        data.getOperands().add("2");
        operation.execute(data, null);
        assertEquals("2.0", data.getOperands().get(data.getOperands().size()-1));
    }

    @Test
    void argumentsExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Multiplication operation = new Multiplication();
        Data data = new Data();
        data.getOperands().add("1");
        data.getOperands().add("adsad");
        assertThrows(ArgumentsException.class, ()-> operation.execute(data, null));
    }

    @Test
    void numberOfOperandsExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Multiplication operation = new Multiplication();
        Data data = new Data();
        data.getOperands().add("1");
        assertThrows(NumberOfOperandsException.class, ()-> operation.execute(data, null));
    }
}