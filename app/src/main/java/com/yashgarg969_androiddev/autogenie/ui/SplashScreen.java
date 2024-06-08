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

import com.yashgarg969_androiddev.autogenie.R;
import com.yashgarg969_androiddev.autogenie.databinding.FragmentSplashScreenBinding;

public class SplashScreen extends Fragment {

    NavController navController;

    FragmentSplashScreenBinding views;
    public SplashScreen() {
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
        views= FragmentSplashScreenBinding.inflate(inflater, container, false);
        return views.getRoot();
    }
    private void init()
    {
        views.splashVideo.setVideoPath("android.resource://"+ getActivity().getPackageName()+"/raw/splash_vid");
        views.splashVideo.setOnPreparedListener(mediaPlayer -> {
            float videoRatio= mediaPlayer.getVideoWidth()/(float)mediaPlayer.getVideoHeight();
            float screenRatio= views.splashVideo.getWidth()/(float)views.splashVideo.getHeight();
            float scaleX= videoRatio/screenRatio;
            if(scaleX>=1f)
                views.splashVideo.setScaleX(scaleX);
            else
                views.splashVideo.setScaleY(2f/scaleX);
            mediaPlayer.isLooping();
            mediaPlayer.start();
        });
        views.splashVideo.setOnCompletionListener(it->{
            navController.navigate(R.id.action_splashScreen_to_welcomeScreen);
        });
    }
}