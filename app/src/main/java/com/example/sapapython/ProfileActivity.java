package com.example.sapapython;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sapapython.Handler.ConnectionHandler;

public class ProfileActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvEmail, tvFirstname, tvMiddlename, tvLastname, tvPhonenumber, tvAddress, tvGender,tvBirthdate, tvBio;
    private ConnectionHandler connectionHandler = new ConnectionHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.backButton);
        tvEmail = findViewById(R.id.profileEmail);
        tvFirstname = findViewById(R.id.profileFirstName);
        tvMiddlename = findViewById(R.id.profileMiddleName);
        tvLastname = findViewById(R.id.profileLastName);
        tvPhonenumber = findViewById(R.id.profilePhoneNumber);
        tvAddress = findViewById(R.id.profileAddress);
        tvGender = findViewById(R.id.profileGender);
        tvBirthdate = findViewById(R.id.profileBirthDate);
        tvBio = findViewById(R.id.profileBio);



        String email = connectionHandler.getEmail();
        String firstname = connectionHandler.getFirstname();
        String middlename = connectionHandler.getMiddlename();
        String lastname = connectionHandler.getLastname();

        tvEmail.setText("Email: " + email);
        tvFirstname.setText("First Name: " + firstname);
        tvMiddlename.setText("Middle Name: " + middlename);
        tvLastname.setText("Last Name: " + lastname);








        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, DashBoardActivity.class);
            startActivity(intent);
        });




    }
}