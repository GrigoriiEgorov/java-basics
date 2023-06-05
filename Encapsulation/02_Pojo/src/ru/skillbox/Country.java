package ru.skillbox;

public class Country {
    private String name;
    private int populationSize;
    private double area;//km^2
    private String capital;
    private boolean seaside;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public boolean isSeaside() {
        return seaside;
    }

    public void setSeaside(boolean seaside) {
        this.seaside = seaside;
    }
}
