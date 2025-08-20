package com.example.sapapython;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sapapython.bottomsheet.LoginBottomSheet;
import com.example.sapapython.bottomsheet.SignupBottomSheet;

public class AuthActivity extends AppCompatActivity {

    private Button loginButton, signupButton;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auth);

        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        loginButton.setOnClickListener(v -> {
            LoginBottomSheet loginSheet = new LoginBottomSheet();
            loginSheet.show(getSupportFragmentManager(), "LoginBottomSheet");
        });

        signupButton.setOnClickListener(v -> {
            SignupBottomSheet signupSheet = new SignupBottomSheet();
            signupSheet.show(getSupportFragmentManager(), "SignupBottomSheet");
        });

    }
}