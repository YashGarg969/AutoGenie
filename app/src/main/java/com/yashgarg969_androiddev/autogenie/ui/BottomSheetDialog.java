package com.yashgarg969_androiddev.autogenie.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.api.ApiResponse;
import com.yashgarg969_androiddev.autogenie.api.Retrofithelper;
import com.yashgarg969_androiddev.autogenie.api.VehicleService;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentBottomSheetDialogBinding;
import com.yashgarg969_androiddev.autogenie.repository.VehicleRepository;
import com.yashgarg969_androiddev.autogenie.utils.VehicleAdapter;
import com.yashgarg969_androiddev.autogenie.utils.VehicleModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.MainViewModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.MainViewModelfactory;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    FragmentBottomSheetDialogBinding views;
    List<VehicleModel> vehicleModelList;
    VehicleAdapter adapter;

    public BottomSheetDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL,R.style.AppBottomSheetDialogTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String minPrice =getArguments().getString("minPrice");
        String maxPrice =getArguments().getString("maxPrice");
        String bodyType =getArguments().getString("bodyType");
        String fuelType =getArguments().getString("fuelType");
        String minDisp =getArguments().getString("minDisp");
        String maxDisp =getArguments().getString("maxDisp");
        String length =getArguments().getString("length");
        String sortColumn= getArguments().getString("sort_column");
        vehicleModelList= new ArrayList<>();
        adapter= new VehicleAdapter(null);
        getCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,sortColumn);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        views= FragmentBottomSheetDialogBinding.inflate(inflater, container, false);
        return views.getRoot();
    }
    public void getCollabRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length, String sort_Column)
    {
        VehicleService vehicleService= Retrofithelper.getInstance().create(VehicleService.class);
        VehicleRepository repository= new VehicleRepository(vehicleService);
        MainViewModel model= new ViewModelProvider(requireActivity(),new MainViewModelfactory(repository, minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length)).get(MainViewModel.class);
        model.loadCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,sort_Column).observe(requireActivity(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                Log.d("TAG2",apiResponse.getRecommendations().toString());
                vehicleModelList=apiResponse.getRecommendations();
                setToLayout(vehicleModelList);
            }
        });
    }

    private void setToLayout(List<VehicleModel> vehicles)
    {
        adapter= new VehicleAdapter(vehicles);
        views.filteredResults.setLayoutManager(new LinearLayoutManager(getActivity()));
        views.filteredResults.setAdapter(adapter);
    }
}