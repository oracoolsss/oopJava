package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.DivisionByZeroException;
import main.exceptions.NumberOfOperandsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {
    @Test
    void successExecute() throws ArgumentsException, NumberOfOperandsException, DivisionByZeroException {
        Division operation = new Division();
        Data data = new Data();
        data.getOperands().add("1");
        data.getOperands().add("2");
        operation.execute(data, null);
        assertEquals("0.5", data.getOperands().get(data.getOperands().size()-1));
    }

    @Test
    void argumentsExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Division operation = new Division();
        Data data = new Data();
        data.getOperands().add("1");
        data.getOperands().add("adsad");
        assertThrows(ArgumentsException.class, ()-> operation.execute(data, null));
    }

    @Test
    void numberOfOperandsExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Division operation = new Division();
        Data data = new Data();
        data.getOperands().add("1");
        assertThrows(NumberOfOperandsException.class, ()-> operation.execute(data, null));
    }

    @Test
    void divisionByZeroExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Division operation = new Division();
        Data data = new Data();
        data.getOperands().add("1");
        data.getOperands().add("0");
        assertThrows(DivisionByZeroException.class, ()-> operation.execute(data, null));
    }
}