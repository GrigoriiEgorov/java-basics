package ru.skillbox;

public class Elevator {
    private int currentFloor;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = 1;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveUp() {
        currentFloor++;
        System.out.println("Current floor is " + currentFloor);
    }

    public void movedDown() {
        currentFloor--;
        System.out.println("Current floor is " + currentFloor);
    }

    public void move(int floor) {
        if (floor <= this.maxFloor && floor >= this.minFloor) {
            if (floor < this.currentFloor) {
                while (floor < this.currentFloor) {
                    this.movedDown();
                }
            } else if (floor > this.currentFloor) {
                while (floor > this.currentFloor) {
                    this.moveUp();
                }
            } else {
                System.out.println("You are hire!");
            }
            return;
        }
        System.out.println("You picked wrong floor");
    }

}
