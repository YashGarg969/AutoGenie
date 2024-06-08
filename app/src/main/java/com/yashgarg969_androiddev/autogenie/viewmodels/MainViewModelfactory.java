package com.yashgarg969_androiddev.autogenie.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yashgarg969_androiddev.autogenie.repository.VehicleRepository;

public class MainViewModelfactory implements ViewModelProvider.Factory {
    VehicleRepository vehicleRepository;
    String minPrice;
    String maxPrice;
    String bodyType;
    String fuelType;
    String minDisp;
    String maxDisp;
    String length;

    public MainViewModelfactory(VehicleRepository vehicleRepository, String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length) {
        this.vehicleRepository = vehicleRepository;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.bodyType = bodyType;
        this.fuelType = fuelType;
        this.minDisp = minDisp;
        this.maxDisp = maxDisp;
        this.length = length;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(vehicleRepository, minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length);
    }
}
