package ru.skillbox;

public class Package {
    public final Dimensions dimensions;
    public final int weight;//g
    public final String address;
    public final boolean abilityFlip;
    public final String registrationNumber;
    public final boolean fragile;

    public Package(Dimensions dimensions, int weight, String address, boolean abilityFlip,
                   String registrationNumber, boolean fragile) {

        this.dimensions = dimensions;
        this.weight = weight;
        this.address = address;
        this.abilityFlip = abilityFlip;
        this.registrationNumber = registrationNumber;
        this.fragile = fragile;
    }

    public Package setAddress(String address){
        return new Package(dimensions, weight,address,abilityFlip,registrationNumber,fragile);
    }
    public Package setDimensions (Dimensions dimensions /*int width, int height, int length*/){
        //Dimensions copyDimensions = dimensions.setDimensions(width, height, length);
        return new Package(dimensions, weight,address,abilityFlip,registrationNumber,fragile);
    }
    public Package setWeight(int weight){
        return new Package(dimensions, weight,address,abilityFlip,registrationNumber,fragile);
    }

    public String toString(){
        return "dimension: " + dimensions + ";\n" +
                "weight: " + weight + " kg; \n" +
                "address: " + address + "; \n" +
                "Ability flip: " + abilityFlip + "; \n" +
                "Registration: " + registrationNumber + "; \n" +
                "fragile: " + fragile + "; \n";

    }
}
