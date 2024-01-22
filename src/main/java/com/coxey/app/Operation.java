package com.coxey.app;

import java.util.function.BinaryOperator;

public class Operation {
    private BinaryOperator<Double> operation;

    public Double apply(Double value1, Double value2) {
        return operation.apply(value1, value2);
    }

    public void setOperation(String operator) {
        switch(operator) {
            case"+":
                operation = Double::sum;
                break;
            case"-":
                operation = (value1, value2) -> value1 - value2;
                break;
            case"*":
                operation = (value1, value2) -> value1 * value2;
                break;
            case"/":
                operation = (value1, value2) -> value1 / value2;
                break;
            case"^":
                operation = Math::pow;
                break;
            default:
                throw new NoOperatorException("Такая операция не поддерживается!");
        }
    }
}
