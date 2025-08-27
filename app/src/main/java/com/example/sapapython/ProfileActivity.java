package com.example.sapapython;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sapapython.Handler.ConnectionHandler;
import com.example.sapapython.Handler.ProfileHandler;

public class ProfileActivity extends AppCompatActivity {

    private ImageView btnBack, detailSchoolImage;
    private TextView tvEmail, tvFirstname, tvMiddlename, tvLastname, tvPhonenumber, tvAddress, tvGender,tvBirthdate, tvBio;
    private Button btnEditProfile;
    private ConnectionHandler connectionHandler = new ConnectionHandler(this);
    private ProfileHandler profileHandler = new ProfileHandler(this);


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
        detailSchoolImage = findViewById(R.id.detailSchoolImage);
        btnEditProfile = findViewById(R.id.btnEditProfile);

        tvEmail.setText("Email: " + connectionHandler.getEmail());
        tvFirstname.setText("First Name: " + connectionHandler.getFirstname());
        tvMiddlename.setText("Middle Name: " + connectionHandler.getMiddlename());
        tvLastname.setText("Last Name: " + connectionHandler.getLastname());
        tvPhonenumber.setText("Phone Number: " + connectionHandler.getPhone());
        tvAddress.setText("Address: " + connectionHandler.getAddress());
        tvGender.setText("Gender: " + connectionHandler.getGender());
        tvBirthdate.setText("Birth Date: " + connectionHandler.getBirthdate());
        tvBio.setText("Bio: " + connectionHandler.getBio());

        Bitmap bitmap = profileHandler.getProfileImage(connectionHandler.getIndex());
        if (bitmap != null) {
            detailSchoolImage.setImageBitmap(bitmap);
        } else {
            detailSchoolImage.setImageResource(R.drawable.students);
        }


        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
            finish();
        });



        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        });




    }
}