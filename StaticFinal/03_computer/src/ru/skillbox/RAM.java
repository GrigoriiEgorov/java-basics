package ru.skillbox;

public class RAM {
    public final String type;
    public final int volume;
    public final int weight;

    public RAM(String type, int volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }


    public String toString() {
        return "RAM:\n" +
                "   Type: " + type + ";\n" +
                "   Volume: " + volume + " GB;\n" +
                "   Weight: " + weight + " g;";
    }
}
