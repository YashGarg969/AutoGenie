package com.yashgarg969_androiddev.autogenie.repository;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FirebaseAuthRepository {

    public FirebaseAuth mAuth;
    String verificationID;

    public FirebaseAuthRepository() {
        mAuth= FirebaseAuth.getInstance();
    }

    public void signIn(PhoneAuthCredential credential, FirebaseResult firebaseResult) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseResult.onSuccess("Success");
                } else {
                    firebaseResult.onFailue("Failure");
                }
            }
        });
    }

    public void createCredential(String otp, FirebaseResult firebaseResult) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp.toString());
        signIn(credential, firebaseResult);
    }

    public void processOTP(String phoneno, Activity activity, FirebaseResult firebaseResult) {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneno)       // Phone number to verify
                .setTimeout(80L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity)                 // (optional) Activity for callback binding
                // If no activity is passed, reCAPTCHA verification can not be used.
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential, firebaseResult);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.d("TAG",e.toString());
                        firebaseResult.onFailue(e.getMessage());
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationID = s;
                        firebaseResult.onSuccess("Otp Sent Successfully");
                    }
                })         // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public boolean alreadyLogin()
    {
        if(mAuth.getCurrentUser()!=null)
            return true;
        return false;
    }

    public interface FirebaseResult
    {
        void onSuccess(String res);
        void onFailue(String res);
    }
}
