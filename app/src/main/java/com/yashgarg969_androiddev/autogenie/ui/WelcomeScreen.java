package com.yashgarg969_androiddev.autogenie.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentWelcomeScreenBinding;
import com.yashgarg969_androiddev.autogenie.repository.DatabaseRepository;
import com.yashgarg969_androiddev.autogenie.repository.FirebaseAuthRepository;
import com.yashgarg969_androiddev.autogenie.viewmodels.AuthViewModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.ProfileViewModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.ProfileViewModelFactory;

public class WelcomeScreen extends Fragment {
    private FragmentWelcomeScreenBinding views;
    private NavController navController;

    public WelcomeScreen() {
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
        views= FragmentWelcomeScreenBinding.inflate(inflater, container, false);
        return views.getRoot();
    }

    private void init()
    {
        FirebaseAuthRepository repository= new FirebaseAuthRepository();
        views.continueBtn.setOnClickListener(view1->
        {
            if(repository.alreadyLogin())
            {
                DatabaseRepository databaseRepository= new DatabaseRepository();
                if(databaseRepository.userExisted())
                    navController.navigate(R.id.action_welcomeScreen_to_inputScreen);
                else
                    navController.navigate(R.id.action_welcomeScreen_to_profileSetupScreen);
            }
            else
                navController.navigate(R.id.action_welcomeScreen_to_loginScreen);
        });
    }
}