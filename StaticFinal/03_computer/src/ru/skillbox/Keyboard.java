package ru.skillbox;

public class Keyboard {
    public final String type;
    public final boolean illumination;
    public final int weight;

    public Keyboard(String type, boolean illumination, int weight) {
        this.type = type;
        this.illumination = illumination;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Keyboard:\n" +
                "   Type: " + type + " ;\n" +
                "   Illumination: " + illumination + ";\n" +
                "   Weight: " + weight + " g;";
    }
}
