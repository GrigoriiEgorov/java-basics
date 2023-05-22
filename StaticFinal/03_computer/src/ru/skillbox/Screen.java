package ru.skillbox;

public class Screen {
    public final int diagonal;
    public final ScreenType type;
    public final int weight;

    public Screen(int diagonal, ScreenType type, int weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }


    public String toString() {
        return "Screen: \n" +
                "   Diagonal: " + diagonal + " inch;\n" +
                "   Type: " + type + ";\n" +
                "   Weight: " + weight + " g;";
    }
}
