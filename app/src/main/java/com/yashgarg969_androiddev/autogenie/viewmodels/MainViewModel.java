package com.yashgarg969_androiddev.autogenie.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yashgarg969_androiddev.autogenie.api.ApiResponse;
import com.yashgarg969_androiddev.autogenie.repository.VehicleRepository;
import com.yashgarg969_androiddev.autogenie.utils.VehicleModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    public VehicleRepository vehicleRepository;
    List<VehicleModel> vehicles;



    /*public LiveData<Vehicle> getVehicles() {
        vehicleRepository.getRecommendations();
        return vehicles;
    }*/

    public MainViewModel(VehicleRepository vehicleRepository, String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length) {
        this.vehicleRepository = vehicleRepository;
        //vehicleRepository.getRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length);
    }

    /*public void loadRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length, VehicleCallback callback)
    {
        vehicleRepository.getRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length, new VehicleRepository.VehicleCallback() {
            @Override
            public void onSuccess(List<VehicleModel> recommendations) {
                callback.onSuccess(recommendations);
            }

            @Override
            public void onError(String error) {
                    callback.onErrorCall(error);
            }
        });
    }*/

    public MutableLiveData<ApiResponse> loadContentRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length)
    {
        return vehicleRepository.getContentRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length);
    }

    public MutableLiveData<ApiResponse> loadCollabRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length, String sortColumn)
    {
        return vehicleRepository.getCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,sortColumn);
    }
    public interface VehicleCallback {
        void onSuccess(List<VehicleModel> recommendations);
        void onErrorCall(String error);
    }


}
