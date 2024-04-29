package edu.school21.program.classes;

import java.util.StringJoiner;

public class Car {
    private String brand;
    private String model;
    private int weight;

    public Car() {
        this.brand = "Default first name";
        this.model = "Default last name";
        this.weight = 0;
    }

    public Car(String firstName, String lastName, int height) {
        this.brand = firstName;
        this.model = lastName;
        this.weight = height;
    }

    public int getWeight(int value) {
        this.weight += value;
        return weight;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add("model='" + model + "'")
                .add("weight=" + weight)
                .toString();
    }
}

