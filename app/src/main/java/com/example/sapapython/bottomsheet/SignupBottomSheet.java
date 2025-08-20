package com.example.sapapython.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sapapython.Handler.AuthenticationHandler;
import com.example.sapapython.Handler.ConnectionHandler;
import com.example.sapapython.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SignupBottomSheet extends BottomSheetDialogFragment {

    private AuthenticationHandler authenticationHandler;
    private TextView emailText, fistnameText, middlenameText, lastnameText, passwordText, confirmPasswordText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_signup, container, false);

        emailText = view.findViewById(R.id.etEmail);
        fistnameText = view.findViewById(R.id.etFirstname);
        middlenameText = view.findViewById(R.id.etMiddlename);
        lastnameText = view.findViewById(R.id.etLastname);
        passwordText = view.findViewById(R.id.etPassword);
        confirmPasswordText = view.findViewById(R.id.etConfirmPassword);
        Button signupBtn = view.findViewById(R.id.btnSignup);





        signupBtn.setOnClickListener(v -> {
            authenticationHandler = new AuthenticationHandler(requireContext());

            String email = emailText.getText().toString().trim();
            String firstname = fistnameText.getText().toString().trim();
            String middlename = middlenameText.getText().toString().trim();
            String lastname = lastnameText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
            String confirmPassword = confirmPasswordText.getText().toString().trim();

            if (email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String status = authenticationHandler.Signup(email, firstname, middlename, lastname, password);

                requireActivity().runOnUiThread(() -> {
                    if(status.equalsIgnoreCase("success")){
                        Toast.makeText(requireContext(), "Success! Wait for approval", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Signup failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });


        return view;
    }
}
