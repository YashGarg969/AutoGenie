package com.yashgarg969_androiddev.autogenie.viewmodels;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.yashgarg969_androiddev.autogenie.repository.FirebaseAuthRepository;
import com.yashgarg969_androiddev.autogenie.repository.checkUserLogin;

public class AuthViewModel extends ViewModel {

    FirebaseAuthRepository authRepository;
    String phone;
    Activity activity;

    public AuthViewModel(FirebaseAuthRepository authRepository, String phone, Activity activity) {
        this.authRepository = authRepository;
        this.phone= phone;
        this.activity= activity;
    }

    public boolean isAlreadyLoggedIn()
    {
        return authRepository.alreadyLogin();
    }
}
