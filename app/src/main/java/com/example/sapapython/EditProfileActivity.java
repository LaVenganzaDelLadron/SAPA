package com.example.sapapython;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sapapython.Handler.ConnectionHandler;
import com.example.sapapython.Handler.ProfileHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity {

    private EditText studentEmail, studentFirstname, studentMiddlename, studentLastname, studentPhone, studentBirthdate, studentAddress, studentBio;
    private Button btnSaveProfile;
    private ImageView imgCoordinatorProfile;
    private Spinner studentGender;
    private Bitmap bitmap = null;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private ConnectionHandler connectionHandler = new ConnectionHandler(this);
    private ProfileHandler profileHandler = new ProfileHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        studentEmail = findViewById(R.id.etEmail);
        studentFirstname = findViewById(R.id.etFirstName);
        studentMiddlename = findViewById(R.id.etMiddleName);
        studentLastname = findViewById(R.id.etLastName);
        studentPhone = findViewById(R.id.etPhone);
        studentBirthdate = findViewById(R.id.etBirthDate);
        studentAddress = findViewById(R.id.etAddress);
        studentBio = findViewById(R.id.etBio);
        studentGender = findViewById(R.id.spinnerGender);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        imgCoordinatorProfile = findViewById(R.id.imgCoordinatorProfile);

        studentEmail.setText(connectionHandler.getEmail());
        studentFirstname.setText(connectionHandler.getFirstname());
        studentMiddlename.setText(connectionHandler.getMiddlename());
        studentLastname.setText(connectionHandler.getLastname());
        studentPhone.setText(connectionHandler.getPhone());
        studentBirthdate.setText(connectionHandler.getBirthdate());
        studentAddress.setText(connectionHandler.getAddress());
        studentBio.setText(connectionHandler.getBio());

        bitmap = profileHandler.getProfileImage(connectionHandler.getIndex());
        if (bitmap != null) {
            imgCoordinatorProfile.setImageBitmap(bitmap);
        } else {
            imgCoordinatorProfile.setImageResource(R.drawable.students);
        }

        btnSaveProfile.setOnClickListener(v -> {
            String email = studentEmail.getText().toString();
            String firstname = studentFirstname.getText().toString();
            String middlename = studentMiddlename.getText().toString();
            String lastname = studentLastname.getText().toString();
            String phone = studentPhone.getText().toString();
            String birthdate = studentBirthdate.getText().toString();
            String address = studentAddress.getText().toString();
            String gender = studentGender.getSelectedItem().toString();
            String bio = studentBio.getText().toString();

            if (bitmap == null) {
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String status = profileHandler.updateProfile(connectionHandler.getIndex(), bitmap, email, firstname, middlename, lastname, phone, birthdate, address, gender, bio);
                runOnUiThread(() -> {
                    if (status.equalsIgnoreCase("success")) {
                        Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to update", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        // Pick image from gallery
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            if (Build.VERSION.SDK_INT >= 29) {
                                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                                bitmap = ImageDecoder.decodeBitmap(source);
                            } else {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            }

                            if (bitmap != null) {
                                imgCoordinatorProfile.setImageBitmap(bitmap);
                                Toast.makeText(this, "Image selected!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Failed to decode image", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            Toast.makeText(this, "Failed to load image: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        findViewById(R.id.btnAddProfile).setOnClickListener(v -> openGallery());

        // Date picker
        studentBirthdate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    EditProfileActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String date = String.format("%04d-%02d-%02d", selectedYear, (selectedMonth + 1), selectedDay);
                        studentBirthdate.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}