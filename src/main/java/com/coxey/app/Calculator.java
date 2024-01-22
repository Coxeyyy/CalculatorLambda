package com.coxey.app;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private CalculatorHelper calculatorHelper = new CalculatorHelper();
    private Operation operation = new Operation();

    public double solveExpression(String expression) {
        String postfix = calculatorHelper.toPostfix(expression);
        String[] message = postfix.split(" ");
        Deque<Double> stackNumber = new ArrayDeque<>();
        for(int i = 0; i < message.length; i++) {
            if(message[i].matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) {
                stackNumber.push(Double.parseDouble(message[i]));
            } else if(calculatorHelper.isOperator(message[i])) {
                double value2 = stackNumber.pop();
                double value1 = stackNumber.pop();
                operation.setOperation(message[i]);
                stackNumber.push(operation.apply(value1,value2));
            }
        }
        return stackNumber.pop();
    }
}
