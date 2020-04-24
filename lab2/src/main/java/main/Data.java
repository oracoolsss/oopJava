package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Data {
    private Map<String, Double> vars = new TreeMap<>();
    private ArrayList<String> operands = new ArrayList<>();

    public Map<String, Double> getVars() {
        return vars;
    }

    public ArrayList<String> getOperands() {
        return operands;
    }

    public Data() {
        vars = new TreeMap<>();
        operands = new ArrayList<>();
    }
}
