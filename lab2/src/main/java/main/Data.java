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

    public void setVar(String name, Double value) {
        vars.put(name, value);
    }
    public Double getVar(String name) {
        return vars.get(name);
    }
    public boolean containsVar(String name) {
        return vars.containsKey(name);
    }

    public ArrayList<Double> getOperands() {
        return operands;
    }
    public void pushValue(Double value) {
        operands.add(value);
    }

    public Double peekValue() {
        return operands.get(valuesSize() - 1);
    }

    public int valuesSize() {
        return operands.size();
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
