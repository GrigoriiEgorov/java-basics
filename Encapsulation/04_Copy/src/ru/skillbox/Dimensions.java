package ru.skillbox;

public class Dimensions {
    public final int width;
    public final int height;
    public final int length;

    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /*public Dimensions setDimensions(int width, int height, int length){
        return new Dimensions(width, height, length);
    }*/

    public int getVolume(){
        return width * height * length;
    }

    @Override
    public String toString() {
        return  "width = " + width +
                ", height = " + height +
                ", length = " + length;
    }
}
