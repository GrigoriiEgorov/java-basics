package ru.skillbox;
public class ArithmeticCalculator {
    int a, b;
    public Operation operation;

    public ArithmeticCalculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int calculate(Operation operation) {
        this.operation = operation;
        int result = 0;
        switch (operation) {
            case ADD -> result = a + b;
            case MULTIPLY -> result = a * b;
            case SUBTRACT -> result = a - b;
        }
        return result;
    }

}