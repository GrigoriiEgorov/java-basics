package ru.skillbox;

public class CPU {
    public final int frequency;
    public final int numberCores;
    public final String vendor;
    public final int weight;

    public CPU(int frequency, int numberCores, String vendor, int weight) {
        this.frequency = frequency;
        this.numberCores = numberCores;
        this.vendor = vendor;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "CPU: \n" +
                "   Frequency: " + frequency + " GHz;\n" +
                "   Number Cores: " + numberCores + ";\n" +
                "   Vendor: " + vendor + ";\n" +
                "   Weight: " + weight + " g;";
    }
}
