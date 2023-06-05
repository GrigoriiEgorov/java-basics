package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Package pack = new Package(new Dimensions(25,35,45),25,"Moscow",
                false,"123DF32",false);
        Dimensions dimensions = new Dimensions(15,25,33);
        Package copyPack = pack.setAddress("Che");
        copyPack = copyPack.setDimensions(new Dimensions(44,55,66));
        //copyPack = copyPack.setDimensions(44,55,66);
        System.out.println(pack + "\n");
        System.out.println(copyPack);
        System.out.println(dimensions);
    }
}
