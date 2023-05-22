package ru.skillbox;

public class StorageDevice {
    public final StorageDeviceType type;
    public final int volume;
    public final int weight;

    public StorageDevice(StorageDeviceType type, int volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }


    public String toString() {
        return "Storage Device:\n" +
                "   Type: " + type + ";\n" +
                "   Volume: " + volume + " TB;\n" +
                "   Weight: " + weight + " g;";
    }
}
