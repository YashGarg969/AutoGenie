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
import android.widget.Toast;

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentProfileSetupScreenBinding;
import com.yashgarg969_androiddev.autogenie.repository.DatabaseRepository;
import com.yashgarg969_androiddev.autogenie.repository.FirebaseAuthRepository;
import com.yashgarg969_androiddev.autogenie.viewmodels.ProfileViewModel;
import com.yashgarg969_androiddev.autogenie.viewmodels.ProfileViewModelFactory;

public class ProfileSetupScreen extends Fragment {

    private FragmentProfileSetupScreenBinding views;
    private NavController navController;
    DatabaseRepository repository;
    String username;
    String currUser;

    public ProfileSetupScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        repository = new DatabaseRepository();
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        views = FragmentProfileSetupScreenBinding.inflate(inflater, container, false);
        return views.getRoot();
    }

    private void init() {

        ProfileViewModel profileViewModel = new ViewModelProvider(requireActivity(), new ProfileViewModelFactory(repository)).get(ProfileViewModel.class);
        views.SetupBtn.setOnClickListener(view1 -> {
            if (views.nameEt.getText().length() == 0)
                views.nameEt.setError("Please provide username");
            else {
                String user= views.nameEt.getText().toString();
                profileViewModel.saveUser(user, new ProfileViewModel.Callbacks() {
                    @Override
                    public void onUserSaved() {
                        Toast.makeText(getActivity(), "Profile Setup Successfully", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_profileSetupScreen_to_inputScreen);
                    }
                    @Override
                    public void onUserFailed(String error) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

    @NonNull
    @Override
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return new ProfileViewModelFactory(repository);
    }
}