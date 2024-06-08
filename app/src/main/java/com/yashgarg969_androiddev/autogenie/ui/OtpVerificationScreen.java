package com.yashgarg969_androiddev.autogenie.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentOtpVerificationScreenBinding;
import com.yashgarg969_androiddev.autogenie.repository.FirebaseAuthRepository;
import com.yashgarg969_androiddev.autogenie.viewmodels.AuthViewModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.AuthViewModelFactory;

public class OtpVerificationScreen extends Fragment {

    private FragmentOtpVerificationScreenBinding views;
    FirebaseAuthRepository authRepository;
    private NavController navController;

    public OtpVerificationScreen() {
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
       views= FragmentOtpVerificationScreenBinding.inflate(inflater , container, false);
       return views.getRoot();
    }
    private void init()
    {
        views.backBtn.setOnClickListener(view1->{
            navController.popBackStack();
        });
        String phone= getArguments().getString("phone");
        phone="+91"+phone;
        authRepository= new FirebaseAuthRepository();
        AuthViewModel authViewModel = new AuthViewModel(authRepository, phone, getActivity());
        authRepository.processOTP(phone, getActivity(),new FirebaseAuthRepository.FirebaseResult() {
                @Override
                public void onSuccess(String res) {
                    Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailue(String res) {
                    Log.d("TAG", res);
                    Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
                }
            });
             views.submitBtn.setOnClickListener(view1->{
                 String otp= views.edt1.getText().toString()+ views.edt2.getText().toString()+ views.edt3.getText().toString()+views.edt4.getText().toString()
                         +views.edt5.getText().toString()+views.edt6.getText().toString();
                 if(otp.length() != 6)
                     Toast.makeText(getActivity(),"Invalid Otp",Toast.LENGTH_LONG).show();
                 else
                 {
                    doVerification(otp);
                 }
             });
             moveCursor();
    }
    private void doVerification(String otp)
    {
        authRepository.createCredential(otp, new FirebaseAuthRepository.FirebaseResult() {
            @Override
            public void onSuccess(String res) {
                Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_otpVerificationScreen_to_profileSetupScreen);
            }
            @Override
            public void onFailue(String res) {
                Toast.makeText(getActivity(),res,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveCursor()
    {
        views.edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!views.edt1.getText().toString().trim().isEmpty())
                    views.edt2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        views.edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!views.edt2.getText().toString().trim().isEmpty())
                    views.edt3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        views.edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!views.edt3.getText().toString().trim().isEmpty())
                    views.edt4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        views.edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!views.edt4.getText().toString().trim().isEmpty())
                    views.edt5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        views.edt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!views.edt5.getText().toString().trim().isEmpty())
                    views.edt6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}