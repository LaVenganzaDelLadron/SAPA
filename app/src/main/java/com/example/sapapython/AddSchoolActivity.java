package com.example.sapapython;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sapapython.Handler.ConnectionHandler;
import com.example.sapapython.Handler.SchoolHandler;

import java.io.IOException;

public class AddSchoolActivity extends AppCompatActivity {

    private static final String TAG = "AddSchoolActivity";

    private ImageView imgSchoolProfile, btnBack;
    private EditText SchoolName, SchoolPhone, SchoolCode, SchoolAddress, SchoolBio;
    private TextView addSchool;
    private SchoolHandler school = new SchoolHandler(this);
    private ConnectionHandler connectionHandler = new ConnectionHandler(this);
    private Button btnAdd;
    private Bitmap bitmap = null;
    private ActivityResultLauncher<Intent> imagePickerLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_school);

        imgSchoolProfile = findViewById(R.id.imgSchoolProfile);
        SchoolName = findViewById(R.id.etSchoolName);
        SchoolPhone = findViewById(R.id.etContactNumber);
        SchoolCode = findViewById(R.id.etZipCode);
        SchoolAddress = findViewById(R.id.etAddress);
        SchoolBio = findViewById(R.id.etBio);
        addSchool = findViewById(R.id.tvTitle);
        btnAdd = findViewById(R.id.btnAddSchool);
        btnBack = findViewById(R.id.backButton);




        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AddSchoolActivity.this, SchoolActivity.class);
            startActivity(intent);
            finish();
        });


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
                                imgSchoolProfile.setImageBitmap(bitmap);
                                Toast.makeText(this, "Image selected!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Failed to decode image", Toast.LENGTH_SHORT).show();
                            }

                        } catch (IOException e) {
                            Toast.makeText(this, "Failed to load image: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.e(TAG, "Image selection failed or canceled.");
                    }
                }
        );

        findViewById(R.id.btnAddProfile).setOnClickListener(v -> openGallery());

        btnAdd.setOnClickListener(v -> {
            String schoolName = SchoolName.getText().toString().trim();
            String schoolPhone = SchoolPhone.getText().toString().trim();
            String schoolCode = SchoolCode.getText().toString().trim();
            String schoolAddress = SchoolAddress.getText().toString().trim();
            String schoolBio = SchoolBio.getText().toString().trim();

            if (bitmap == null) {
                Toast.makeText(this, "Please select an image first!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                String status = school.addSchool(
                        bitmap,
                        schoolName,
                        schoolPhone,
                        schoolCode,
                        schoolAddress,
                        schoolBio,
                        connectionHandler.getIndex()
                );


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(this, "School Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to add school: ", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openGallery() {
        Log.d(TAG, "Opening gallery...");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}
