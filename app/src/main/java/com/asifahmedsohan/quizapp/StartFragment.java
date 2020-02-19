package com.asifahmedsohan.quizapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartFragment extends Fragment {

    private static final String TAG = "StartFragment";

    private ProgressBar startProgress;
    private TextView startFeedbackText;

    private FirebaseAuth mAuth;

    private NavController navController;
// ...
// Initialize Firebase Auth

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        navController = Navigation.findNavController(view);

        startProgress = view.findViewById(R.id.start_progress);
        startFeedbackText = view.findViewById(R.id.start_feedback);

        startFeedbackText.setText("Checking user account...");


    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){

            startFeedbackText.setText("Creating user account...");

            //create a new account
            mAuth.signInAnonymously()
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startFeedbackText.setText("Account created...");
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInAnonymously:success");
                                navController.navigate(R.id.action_startFragment_to_listFragment);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInAnonymously:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed!",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


        } else {
            startFeedbackText.setText("Logged in...");
            //navigate to homepage
            navController.navigate(R.id.action_startFragment_to_listFragment);
        }

    }
}
