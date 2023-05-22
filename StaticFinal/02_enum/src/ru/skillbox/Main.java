package ru.skillbox;

public class Main {
    public static void main(String[] args) {
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator(3,4);
        System.out.println( arithmeticCalculator.calculate(Operation.MULTIPLY));
    }
}