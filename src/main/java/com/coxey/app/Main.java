package com.coxey.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение без знака равно и с пробелами после каждого числа/оператора: ");
        String infixExpression = in.nextLine();
        System.out.println("Ответ: " + calculator.solveExpression(infixExpression));
    }
}