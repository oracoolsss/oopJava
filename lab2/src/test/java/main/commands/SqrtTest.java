package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfOperandsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {
    @Test
    void successExecute() throws ArgumentsException, NumberOfOperandsException {
        Sqrt operation = new Sqrt();
        Data data = new Data();
        data.getOperands().add("4");
        operation.execute(data, null);
        assertEquals("2.0", data.getOperands().get(data.getOperands().size()-1));
    }

    @Test
    void argumentsExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Sqrt operation = new Sqrt();
        Data data = new Data();
        data.getOperands().add("adsad");
        assertThrows(ArgumentsException.class, ()-> operation.execute(data, null));
    }

    @Test
    void numberOfOperandsExceptionExecute() throws ArgumentsException, NumberOfOperandsException {
        Sqrt operation = new Sqrt();
        Data data = new Data();
        assertThrows(NumberOfOperandsException.class, ()-> operation.execute(data, null));
    }

    @Test
    void argumentsExceptionExecute2() throws ArgumentsException, NumberOfOperandsException {
        Sqrt operation = new Sqrt();
        Data data = new Data();
        data.getOperands().add("-2");
        assertThrows(ArgumentsException.class, ()-> operation.execute(data, null));
    }
}