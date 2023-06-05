package ru.skillbox;

public class Guitar {
    private String fabricator;
    private int numberOfStrings;
    private String color;
    private boolean soundPickup;

    public Guitar(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public String getFabricator() {
        return fabricator;
    }

    public void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSoundPickup() {
        return soundPickup;
    }

    public void setSoundPickup(boolean soundPickup) {
        this.soundPickup = soundPickup;
    }
}
