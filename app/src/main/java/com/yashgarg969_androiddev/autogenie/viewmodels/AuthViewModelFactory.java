package com.yashgarg969_androiddev.autogenie.viewmodels;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yashgarg969_androiddev.autogenie.repository.FirebaseAuthRepository;

public class AuthViewModelFactory implements ViewModelProvider.Factory {

    FirebaseAuthRepository repository;
    Activity activity;
    String phone;

    public AuthViewModelFactory(FirebaseAuthRepository repository, Activity activity, String phone) {
        this.repository = repository;
        this.activity = activity;
        this.phone = phone;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //return ViewModelProvider.Factory.super.create(modelClass);
        return (T) new AuthViewModel(repository,phone,activity);
    }
}
