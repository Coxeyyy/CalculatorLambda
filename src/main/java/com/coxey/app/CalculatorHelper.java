package com.coxey.app;

import java.util.ArrayDeque;
import java.util.Deque;
public class CalculatorHelper {
    /**
     * Метод переводит инфиксное выражение в постфиксное
     * Пример:
     * Инфиксное: 2 + 2 * 2
     * Постфиксное: 2 2 2 * +
     */
    protected String toPostfix(String infixExpression) {
        String result = "";
        Deque<Character> operatorDeque = new ArrayDeque<>();
        for(int i = 0; i< infixExpression.length(); i++) {
            char character = infixExpression.charAt(i);
            if (operationPriority(character) > 0) {
                while(operatorDeque.isEmpty() == false &&
                operationPriority(operatorDeque.peek()) >= operationPriority(character)) {
                    result += operatorDeque.pop();
                }
                operatorDeque.push(character);
            } else if (character == ')') {
                char temp = operatorDeque.pop();
                while(temp != '(') {
                    result += ' ';
                    result += temp;
                    temp = operatorDeque.pop();
                }
            } else if (character == '(') {
                operatorDeque.push(character);
            } else {
                result += character;
            }
        }
        do {
            if(operatorDeque.isEmpty()){
                throw new NoOperatorException("Недостаточно операторов для выполнения действий");
            }
            result += ' ';
            result += operatorDeque.pop();
        } while(!operatorDeque.isEmpty());
        return result;
    }

    /**
     * Метод проверяет является ли переданная операция поддерживаемой
     */
    protected boolean isOperator(String operator) {
        switch(operator) {
            case"+":
            case"-":
            case"*":
            case"/":
            case"^":
                return true;
            default:
                return false;
        }
    }

    /**
     * Метод устанавливает приоритет операции
     * чем выше число, тем выше приоритет
     */
    private int operationPriority(char character) {
        switch(character) {
            case'+':
            case'-':
                return 1;
            case'*':
            case'/':
                return 2;
            case'^':
                return 3;
            default:
                return -1;
        }
    }

}
