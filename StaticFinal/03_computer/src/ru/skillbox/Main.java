package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Computer computer1 = new Computer("acer", "number1");
        computer1.setCPU(2, 4, "intel", 100);
        computer1.setRAM("DDR3", 8, 250);
        computer1.setStorageDevice(StorageDeviceType.SSD,2,300);
        computer1.setScreen(27,ScreenType.IPS,1500);
        computer1.setKeyboard("Mechanical keyboard", true,400);

        Computer computer2 = new Computer("lenovo", "number2");
        computer2.setCPU(3, 2, "AMD", 100);
        computer2.setRAM("DDR3", 5, 270);
        computer2.setStorageDevice(StorageDeviceType.HDD,1,200);
        computer2.setScreen(27,ScreenType.TN,1600);
        computer2.setKeyboard("Mechanical keyboard", false,300);


        System.out.println(computer1);
        System.out.println(computer2);

    }
}
