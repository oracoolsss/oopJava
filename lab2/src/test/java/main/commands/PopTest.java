package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfOperandsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopTest {
    @Test
    void successExecute() throws ArgumentsException, NumberOfOperandsException {
        Pop operation = new Pop();
        Data data = new Data();
        data.getOperands().add("2");
        operation.execute(data, null);
        assertEquals(0, data.getOperands().size());
    }

    @Test
    void successExecute2() throws ArgumentsException, NumberOfOperandsException {
        Pop operation = new Pop();
        Data data = new Data();
        operation.execute(data, null);
        assertEquals(0, data.getOperands().size());
    }
}