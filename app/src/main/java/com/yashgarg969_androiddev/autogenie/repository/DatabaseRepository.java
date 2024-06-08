package com.yashgarg969_androiddev.autogenie.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashgarg969_androiddev.autogenie.callbacks.*;

public class DatabaseRepository {
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuthRepository repository;

    String currUser;

    public DatabaseRepository() {
        database= FirebaseDatabase.getInstance();
        reference=database.getReference();
        repository= new FirebaseAuthRepository();
        currUser= repository.mAuth.getCurrentUser().getPhoneNumber();
    }

    public void saveToDatabase(String username, Callback callback)
    {
        reference.child("Users").child(currUser).setValue(username).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailue(e.getMessage());
            }
        });
    }

    public boolean userExisted()
    {

        final boolean[] flag = {false};
        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(currUser))
                    flag[0] =true;
                else
                    flag[0]=false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    flag[0]=false;
            }
        });
        return flag[0];
    }
}
