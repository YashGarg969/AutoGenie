package com.yashgarg969_androiddev.autogenie.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.yashgarg969_androiddev.autogenie.utils.VehicleModel;

public class ApiResponse {
    @SerializedName("num_available_cars")
    private int numAvailableCars;

    @SerializedName("recommendations")
    private List<VehicleModel> recommendations;

    public ApiResponse(int numAvailableCars, List<VehicleModel> recommendations) {
        this.numAvailableCars = numAvailableCars;
        this.recommendations = recommendations;
    }

    public int getNumAvailableCars() {
        return numAvailableCars;
    }

    public void setNumAvailableCars(int numAvailableCars) {
        this.numAvailableCars = numAvailableCars;
    }

    public List<VehicleModel> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<VehicleModel> recommendations) {
        this.recommendations = recommendations;
    }
}
