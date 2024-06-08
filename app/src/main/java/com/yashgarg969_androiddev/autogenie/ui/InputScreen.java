package com.yashgarg969_androiddev.autogenie.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentInputScreenBinding;
import com.yashgarg969_androiddev.autogenie.utils.VehicleModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class InputScreen extends Fragment {

    List<String> bodyTypesList;
    List<String> fuelTypesList;
    List<String> length_filterList;
    MainViewModel mainViewModel;
    List<VehicleModel> myResult= new ArrayList<>();
    private FragmentInputScreenBinding views;
    private NavController navController;
    public InputScreen() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        setupViews();
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        views= FragmentInputScreenBinding.inflate(inflater, container, false);
        return views.getRoot();
    }

    private void setupViews()
    {
        bodyTypesList= new ArrayList<>();
        bodyTypesList.add("SUV");
        bodyTypesList.add("Sedan");
        bodyTypesList.add("Hatchback");
        fuelTypesList= new ArrayList<>();
        fuelTypesList.add("Electric");
        fuelTypesList.add("Petrol");
        fuelTypesList.add("Diesel");
        length_filterList= new ArrayList<>();
        length_filterList.add("more");
        length_filterList.add("less");
        views.lengthFilterEt.setInputType(0);
        views.bodytypeEt.setInputType(0);
        views.fueltypeEt.setInputType(0);
        views.fueltypeEt.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.dropdown_item,fuelTypesList));
        views.bodytypeEt.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.dropdown_item,bodyTypesList));
        views.lengthFilterEt.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.dropdown_item,length_filterList));
    }

    private void init()
    {
        views.submitBtn.setOnClickListener(view1->
        {
            String minPrice= views.minPrice.getText().toString();
            if(isNull(minPrice))
                    views.minPrice.setError("This field is essential");
            String maxPrice= views.maxPrice.getText().toString();
            if(isNull(maxPrice))
                views.maxPrice.setError("This field is essential");
            String bodyType= views.bodytypeEt.getText().toString();
            if(isNull(bodyType))
                views.bodytypeEt.setError("This field is essential");
            String fuelType= views.fueltypeEt.getText().toString();
            if(isNull(fuelType))
                views.fueltypeEt.setError("This field is essential");
            String minDisp= views.displacementMinEt.getText().toString();
            if(isNull(minDisp))
                views.displacementMinEt.setError("This field is essential");
            String maxDisp= views.maxDisplacementEt.getText().toString();
            if(isNull(maxDisp))
                views.maxDisplacementEt.setError("This field is essential");
            String length= views.lengthFilterEt.getText().toString();
            if(isNull(length))
                views.lengthFilterEt.setError("This field is essential");
            else
            {
                Bundle bundle= new Bundle();
                bundle.putString("minPrice",minPrice);
                bundle.putString("maxPrice",maxPrice);
                bundle.putString("bodyType",bodyType);
                bundle.putString("fuelType",fuelType);
                bundle.putString("minDisp",minDisp);
                bundle.putString("maxDisp",maxDisp);
                bundle.putString("length",length);
               navController.navigate(R.id.action_inputScreen_to_resultScreen,bundle);
            }

//            VehicleService vehicleService = Retrofithelper.getInstance().create(VehicleService.class);
//            VehicleRepository repository = new VehicleRepository(vehicleService);
//            mainViewModel = new ViewModelProvider(requireActivity(), new MainViewModelfactory(repository, minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length)).get(MainViewModel.class);
//            mainViewModel.loadRecommendations(minPrice, maxPrice, bodyType, fuelType, minDisp, maxDisp, length, new MainViewModel.VehicleCallback() {
//                @Override
//                public void onSuccess(List<VehicleModel> recommendations) {
//                    myResult= recommendations;
//                    Toast.makeText(getActivity(),myResult.get(0).toString(),Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onErrorCall(String error) {
//                        Toast.makeText(requireActivity(),error,Toast.LENGTH_SHORT).show();
//                }
//            });
        });
    }

    private boolean isNull(String txt)
    {
        return txt.length() == 0;
    }
}