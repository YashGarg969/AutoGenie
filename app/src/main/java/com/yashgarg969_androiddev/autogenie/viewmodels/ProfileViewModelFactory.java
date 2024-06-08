package com.yashgarg969_androiddev.autogenie.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yashgarg969_androiddev.autogenie.repository.DatabaseRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {

    DatabaseRepository repository;

    public ProfileViewModelFactory(DatabaseRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(repository);
    }
}
