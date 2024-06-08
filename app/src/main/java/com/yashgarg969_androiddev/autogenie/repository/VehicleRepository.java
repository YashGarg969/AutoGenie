package com.yashgarg969_androiddev.autogenie.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.yashgarg969_androiddev.autogenie.api.ApiResponse;
import com.yashgarg969_androiddev.autogenie.api.Retrofithelper;
import com.yashgarg969_androiddev.autogenie.api.VehicleService;
import com.yashgarg969_androiddev.autogenie.utils.VehicleModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRepository {
    private VehicleService vehicleService;
    List<VehicleModel> myRecommendations = new ArrayList<>();
    List<VehicleModel> myCollabRecommendations= new ArrayList<>();
    public VehicleRepository(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void getRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length, VehicleCallback callback) {
//        Response<List<Vehicle>> response= vehicleService.getRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length);
//        if(response!=null && response.body()!=null)
//                vehicleMutableLiveData.postValue(response.body());

        vehicleService = Retrofithelper.getInstance().create(VehicleService.class);
        Call<ApiResponse> myCall = vehicleService.getRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length);
        myCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    ApiResponse apiResponse = response.body();
                    //List<VehicleModel> myVehicles= apiResponse.getRecommendations();
                    myRecommendations = apiResponse.getRecommendations();
                    if (callback != null) {
                        callback.onSuccess(myRecommendations);
                    }
                }
                else
                    Log.d("TAG","Empty response"+response);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                if (callback != null) {
                    Log.d("TAG", t.getMessage());
                    callback.onError("Network Error: " + t.getMessage());
                }
            }
        });
    }

    public MutableLiveData<ApiResponse> getCollabRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length, String sortColumn) {
        vehicleService = Retrofithelper.getInstance().create(VehicleService.class);
        Call<ApiResponse> collabCall = vehicleService.getCollabRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length, sortColumn);
        final MutableLiveData<ApiResponse> collabRecommendations=new MutableLiveData<>();
        collabCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!=null)
                        collabRecommendations.setValue(response.body());
                    else
                        collabRecommendations.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                collabRecommendations.setValue(null);
            }
        });
        return collabRecommendations;
    }

    public MutableLiveData<ApiResponse> getContentRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length)
    {
        vehicleService = Retrofithelper.getInstance().create(VehicleService.class);
        Call<ApiResponse> call= vehicleService.getRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length);
        final MutableLiveData<ApiResponse> contentRecommendations=new MutableLiveData<>();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!=null)
                        contentRecommendations.setValue(response.body());
                    else
                        contentRecommendations.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                contentRecommendations.setValue(null);
            }
        });
        return contentRecommendations;
    }


    public interface VehicleCallback {
        void onSuccess(List<VehicleModel> recommendations);

        void onError(String error);
    }
}
