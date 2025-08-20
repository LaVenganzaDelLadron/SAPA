package com.example.sapapython.bottomsheet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sapapython.DashBoardActivity;
import com.example.sapapython.Handler.AuthenticationHandler;
import com.example.sapapython.MainActivity;
import com.example.sapapython.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LoginBottomSheet extends BottomSheetDialogFragment {

    private TextView emailText, passwordText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_login, container, false);

        emailText = view.findViewById(R.id.etEmail);
        passwordText = view.findViewById(R.id.etPassword);


        Button loginBtn = view.findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(v -> {
            AuthenticationHandler authenticationHandler = new AuthenticationHandler(requireContext());

            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            String status = authenticationHandler.Login(email, password);

            if(status.equalsIgnoreCase("success")){
                Intent intent = new Intent(requireContext(), DashBoardActivity.class);
                startActivity(intent);
                dismiss();
                Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
}
