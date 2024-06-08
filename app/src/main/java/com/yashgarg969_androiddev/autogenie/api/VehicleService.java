package com.yashgarg969_androiddev.autogenie.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VehicleService {

    @GET("/recommendations")
    public Call<ApiResponse> getRecommendations(
            @Query("min_price") String minPrice,
            @Query("max_price") String maxPrice,
            @Query("body_type") String bodyType,
            @Query("fuel_type") String fuelType,
            @Query("displacement_min") String displacementMin,
            @Query("displacement_max") String displacementMax,
            @Query("length_filter") String lengthFilter
    );
    @GET("/collabRecommendations")
    public Call<ApiResponse> getCollabRecommendations(
            @Query("min_price") String minPrice,
            @Query("max_price") String maxPrice,
            @Query("body_type") String bodyType,
            @Query("fuel_type") String fuelType,
            @Query("displacement_min") String displacementMin,
            @Query("displacement_max") String displacementMax,
            @Query("length_filter") String lengthFilter,
            @Query("sort_column") String sortColumn
    );
    @GET("/matrixRecommendations")
    public Call<ApiResponse> getMatrixRecommendations();
}
