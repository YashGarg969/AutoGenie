package com.yashgarg969_androiddev.autogenie.utils;

import com.google.gson.annotations.SerializedName;

public class VehicleModel {
    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("Ex-Showroom_Price")
    private double exShowroomPrice;

    @SerializedName("Body_Type")
    private String bodyType;

    @SerializedName("Length")
    private double length;

    @SerializedName("Fuel_Type")
    private String fuelType;

    @SerializedName("Displacement")
    private double displacement;

    public VehicleModel(String make, String model, double exShowroomPrice, String bodyType, double length, String fuelType, double displacement) {
        this.make = make;
        this.model = model;
        this.exShowroomPrice = exShowroomPrice;
        this.bodyType = bodyType;
        this.length = length;
        this.fuelType = fuelType;
        this.displacement = displacement;
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

    public double getExShowroomPrice() {
        return exShowroomPrice;
    }

    public void setExShowroomPrice(double exShowroomPrice) {
        this.exShowroomPrice = exShowroomPrice;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }
}

