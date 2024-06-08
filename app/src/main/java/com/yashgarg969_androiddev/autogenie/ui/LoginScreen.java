package com.yashgarg969_androiddev.autogenie.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentLoginScreenBinding;

public class LoginScreen extends Fragment {

    private FragmentLoginScreenBinding views;
    private NavController navController;

    public LoginScreen() {
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
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        views= FragmentLoginScreenBinding.inflate(inflater, container, false);
        return views.getRoot();
    }

    private void init()
    {
        views.backBtn.setOnClickListener(view1->{
            navController.popBackStack();
        });
        views.btnNext.setOnClickListener(view1->{
            String phoneNumber= views.mobileEt.getText().toString();
            if(phoneNumber.length()!=10)
                views.mobileEt.setError("Check your mobile number");
            else
            {
                Bundle bundle= new Bundle();
                bundle.putString("phone",phoneNumber);
                navController.navigate(R.id.action_loginScreen_to_otpVerificationScreen,bundle);
            }
        });
    }
}