package com.yashgarg969_androiddev.autogenie.utils;

public class Vehicle {

    String make;
    String model;
    String showroomprice;

    public Vehicle(String make, String model, String showroomprice) {
        this.make = make;
        this.model = model;
        this.showroomprice = showroomprice;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getShowroomprice() {
        return showroomprice;
    }

    public void setShowroomprice(String showroomprice) {
        this.showroomprice = showroomprice;
    }
}
