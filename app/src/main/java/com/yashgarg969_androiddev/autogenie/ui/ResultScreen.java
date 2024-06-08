package com.yashgarg969_androiddev.autogenie.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.api.ApiResponse;
import com.yashgarg969_androiddev.autogenie.api.Retrofithelper;
import com.yashgarg969_androiddev.autogenie.api.VehicleService;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentResultScreenBinding;
import com.yashgarg969_androiddev.autogenie.repository.VehicleRepository;
import com.yashgarg969_androiddev.autogenie.utils.VehicleAdapter;
import com.yashgarg969_androiddev.autogenie.utils.VehicleModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.MainViewModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.MainViewModelfactory;

import java.util.ArrayList;
import java.util.List;

public class ResultScreen extends Fragment {


    private FragmentResultScreenBinding views;
    VehicleAdapter adapter;
    private NavController navController;
    private List<VehicleModel> vehicleModelList= new ArrayList<>();
    MainViewModel mainViewModel;

    public ResultScreen() {
        // Required empty public constructor
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
        navController= Navigation.findNavController(view);
        adapter= new VehicleAdapter(null);

        /*ExecutorService service= Executors.newFixedThreadPool(4);
        service.submit(new Runnable() {
            @Override
            public void run() {
                views.shimmerEffect.stopShimmer();
                Toast.makeText(getActivity(),"Stopped",Toast.LENGTH_SHORT).show();
                VehicleService vehicleService = Retrofithelper.getInstance().create(VehicleService.class);
                VehicleRepository repository = new VehicleRepository(vehicleService);
                mainViewModel = new ViewModelProvider(requireActivity(), new MainViewModelfactory(repository, minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length)).get(MainViewModel.class);
                mainViewModel.loadRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length, new MainViewModel.VehicleCallback() {
                    @Override
                    public void onSuccess(List<VehicleModel> recommendations) {
                        vehicleModelList= recommendations;
                        Log.d("Recommendations",recommendations.toString());
                        setToLayout();
                        //Toast.makeText(getActivity(),myResult.get(0).toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onErrorCall(String error) {
                        Toast.makeText(requireActivity(),error,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        */

        getRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length);

        views.safetyRatings.setOnClickListener(view1 -> {
            //getCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,"Safety_Ratings");
            goToCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,"Safety_Ratings");
        });
        views.avgKm.setOnClickListener(view1->{
            goToCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,"Avg_KM");
        });
        views.avgMaintenance.setOnClickListener(view1->{
            goToCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,"Avg_Maintenance");
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        views= FragmentResultScreenBinding.inflate(inflater, container, false);
        return views.getRoot();
    }

    private void setToLayout(List<VehicleModel> vehicles)
    {
        adapter= new VehicleAdapter(vehicles);
        views.recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        views.recView.setAdapter(adapter);
    }

    private void getRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length)
    {
        VehicleService vehicleService = Retrofithelper.getInstance().create(VehicleService.class);
        VehicleRepository repository = new VehicleRepository(vehicleService);
        mainViewModel = new ViewModelProvider(requireActivity(), new MainViewModelfactory(repository, minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length)).get(MainViewModel.class);
        /*mainViewModel.loadRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length, new MainViewModel.VehicleCallback() {
            @Override
            public void onSuccess(List<VehicleModel> recommendations) {
                requireActivity().runOnUiThread(()->{
                    views.shimmerEffect.stopShimmer();
                    vehicleModelList= recommendations;
                    setToLayout(recommendations);
                    Toast.makeText(getActivity(),recommendations.toString(),Toast.LENGTH_SHORT).show();
                });

            }
            @Override
            public void onErrorCall(String error) {
                requireActivity().runOnUiThread(()-> {
                    Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
                });
            }
        });*/
        mainViewModel.loadContentRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length).observe(requireActivity(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                Toast.makeText(getActivity(),apiResponse.toString(),Toast.LENGTH_SHORT).show();
                Log.d("TAG",String.valueOf(apiResponse.getNumAvailableCars()));
                Log.d("TAG",String.valueOf(apiResponse.getRecommendations()).toString());
                if(!apiResponse.getRecommendations().isEmpty())
                {
                    views.filters.setVisibility(View.VISIBLE);
                    views.recView.setVisibility(View.VISIBLE);
                    vehicleModelList= apiResponse.getRecommendations();
                    Log.d("Recoo",vehicleModelList.toString());
                    setToLayout(vehicleModelList);
                }
                else {
                    views.noDataLayout.setVisibility(View.VISIBLE);
                    views.searchBtn.setOnClickListener(view1->{
                        navController.popBackStack();
                    });
                }
            }
        });
    }

    public void getCollabRecommendations(String minPrice, String maxPrice, String bodyType, String fuelType, String minDisp, String maxDisp, String length, String sort_Column)
    {
        VehicleService vehicleService= Retrofithelper.getInstance().create(VehicleService.class);
        VehicleRepository repository= new VehicleRepository(vehicleService);
        MainViewModel model= new ViewModelProvider(requireActivity(),new MainViewModelfactory(repository, minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length)).get(MainViewModel.class);
        model.loadCollabRecommendations(minPrice,maxPrice,bodyType,fuelType,minDisp,maxDisp,length,sort_Column).observe(requireActivity(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                Toast.makeText(getActivity(),apiResponse.toString(),Toast.LENGTH_SHORT).show();
                Log.d("TAG",apiResponse.getRecommendations().toString());
                int size=vehicleModelList.size();
                vehicleModelList.clear();
                adapter.notifyItemRangeRemoved(0,size);

            }
        });
    }

    public void goToCollabRecommendations(String minPrice, String maxPrice,String bodyType,String fuelType,String minDisp, String maxDisp,String length,String sort_Column)
    {
        Bundle bundle= new Bundle();
        bundle.putString("minPrice",minPrice);
        bundle.putString("maxPrice",maxPrice);
        bundle.putString("bodyType",bodyType);
        bundle.putString("fuelType",fuelType);
        bundle.putString("minDisp",minDisp);
        bundle.putString("maxDisp",maxDisp);
        bundle.putString("length",length);
        bundle.putString("sort_column", sort_Column);
        navController.navigate(R.id.action_resultScreen_to_bottomSheetDialog,bundle);
    }

}