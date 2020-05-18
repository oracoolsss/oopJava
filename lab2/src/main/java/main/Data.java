package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Data {
    private Map<String, Double> vars;
    private ArrayList<Double> operands;

    public Map<String, Double> getVars() {
        return vars;
    }

    public ArrayList<Double> getOperands() {
        return operands;
    }

    public Double popValue() {
        Double retValue = operands.get(operands.size() - 1);
        operands.remove(operands.size() - 1);
        return  retValue;
    }

    public Data() {
        vars = new TreeMap<>();
        operands = new ArrayList<>();
    }
}
