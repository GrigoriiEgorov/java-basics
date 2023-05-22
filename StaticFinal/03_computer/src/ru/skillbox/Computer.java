package ru.skillbox;

public class Computer {

    CPU cpu;
    RAM ram;
    StorageDevice storageDevice;
    Screen screen;
    Keyboard keyboard;
    private int weightCPU, weightRAM, weightStorageDevice, weightScreen, weightKeyboard;

    public final String vendor;
    public final String name;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public void setCPU(int frequency, int numberCores, String vendor, int weight) {
        cpu = new CPU(frequency, numberCores, vendor, weight);
        weightCPU = weight;
    }

    public void setRAM(String type, int volume, int weight) {
        ram = new RAM(type, volume, weight);
        weightRAM = weight;
    }

    public void setStorageDevice(StorageDeviceType type, int volume, int weight) {
        storageDevice = new StorageDevice(type, volume, weight);
        weightStorageDevice = weight;
    }

    public void setScreen(int diagonal, ScreenType type, int weight) {
        screen = new Screen(diagonal, type, weight);
        weightScreen = weight;
    }

    public void setKeyboard(String type, boolean illumination, int weight) {
        keyboard = new Keyboard(type, illumination, weight);
        weightKeyboard = weight;
    }

    public CPU getCpu() {
        return cpu;
    }

    public RAM getRam() {
        return ram;
    }

    public StorageDevice getStorageDevice() {
        return storageDevice;
    }

    public Screen getScreen() {
        return screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public int getTotalWeight() {
        return (weightCPU + weightRAM + weightStorageDevice + weightScreen + weightKeyboard);
    }

    public String toString() {
        return "Vendor: " + vendor + ";\n" +
                "Name: " + name + ";\n" +
                cpu + "\n" +
                ram + "\n" +
                storageDevice + "\n" +
                screen + "\n" +
                keyboard + "\n" +
                "Total weight: " + getTotalWeight() + " g;\n\n";
    }


}
