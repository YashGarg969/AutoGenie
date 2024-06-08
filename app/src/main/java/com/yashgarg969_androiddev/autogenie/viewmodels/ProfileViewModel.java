package com.yashgarg969_androiddev.autogenie.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.yashgarg969_androiddev.autogenie.callbacks.Callback;
import com.yashgarg969_androiddev.autogenie.repository.DatabaseRepository;

import java.io.Closeable;

public class ProfileViewModel extends ViewModel {
        DatabaseRepository databaseRepository;

    public ProfileViewModel(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }
    public boolean userAlreadyExists()
    {
        return databaseRepository.userExisted();
    }

    public void saveUser(String userName, Callbacks callbacks)
    {
        databaseRepository.saveToDatabase(userName, new Callback() {
            @Override
            public void onSuccess() {
                callbacks.onUserSaved();
            }

            @Override
            public void onFailue(String error) {
                callbacks.onUserFailed(error);
            }
        });
    }

    public interface Callbacks
    {
        void onUserSaved();
        void onUserFailed(String error);
    }
}
