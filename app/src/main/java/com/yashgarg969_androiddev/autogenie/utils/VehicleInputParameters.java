package com.yashgarg969_androiddev.autogenie.utils;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class VehicleInputParameters implements Parcelable {

    String minPrice;
    String maxPrice;
    String bodyType;
    String fuelType;
    String minDisplacement;
    String maxDisplacement;
    String lengthFilter;

    public VehicleInputParameters(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisplacement, String maxDisplacement, String lengthFilter) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.minDisplacement = minDisplacement;
        this.maxDisplacement = maxDisplacement;
        this.lengthFilter = lengthFilter;
    }

    protected VehicleInputParameters(Parcel in) {
        minPrice = in.readString();
        maxPrice = in.readString();
        bodyType = in.readString();
        fuelType = in.readString();
        minDisplacement = in.readString();
        maxDisplacement = in.readString();
        lengthFilter = in.readString();
    }

    public static final Creator<VehicleInputParameters> CREATOR = new Creator<VehicleInputParameters>() {
        @Override
        public VehicleInputParameters createFromParcel(Parcel in) {
            return new VehicleInputParameters(in);
        }

        @Override
        public VehicleInputParameters[] newArray(int size) {
            return new VehicleInputParameters[size];
        }
    };

//    public String getMinPrice() {
//        return minPrice;
//    }
//
//    public void setMinPrice(String minPrice) {
//        this.minPrice = minPrice;
//    }
//
//    public String getMaxPrice() {
//        return maxPrice;
//    }
//
//    public void setMaxPrice(String maxPrice) {
//        this.maxPrice = maxPrice;
//    }
//
//    public String getBodyType() {
//        return bodyType;
//    }
//
//    public void setBodyType(String bodyType) {
//        this.bodyType = bodyType;
//    }
//
//    public String getFuelType() {
//        return fuelType;
//    }
//
//    public void setFuelType(String fuelType) {
//        this.fuelType = fuelType;
//    }
//
//    public String getMinDisplacement() {
//        return minDisplacement;
//    }
//
//    public void setMinDisplacement(String minDisplacement) {
//        this.minDisplacement = minDisplacement;
//    }
//
//    public String getMaxDisplacement() {
//        return maxDisplacement;
//    }
//
//    public void setMaxDisplacement(String maxDisplacement) {
//        this.maxDisplacement = maxDisplacement;
//    }
//
//    public String getLengthFilter() {
//        return lengthFilter;
//    }
//
//    public void setLengthFilter(String lengthFilter) {
//        this.lengthFilter = lengthFilter;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(minPrice);
        parcel.writeString(maxPrice);
        parcel.writeString(bodyType);
        parcel.writeString(fuelType);
        parcel.writeString(minDisplacement);
        parcel.writeString(maxDisplacement);
        parcel.writeString(lengthFilter);
    }
}
