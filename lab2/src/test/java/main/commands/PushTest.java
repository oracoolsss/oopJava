package main.commands;

import main.Data;
import main.exceptions.ArgumentsException;
import main.exceptions.NumberOfOperandsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PushTest {
    @Test
    void successExecute() throws ArgumentsException, NumberOfOperandsException {
        Push operation = new Push();
        Data data = new Data();
        operation.execute(data, "34");
        assertEquals(1, data.getOperands().size());
    }
}