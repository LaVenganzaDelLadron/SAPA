package com.example.sapapython;

import android.app.DatePickerDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sapapython.Handler.StudentHandler;

import java.io.IOException;
import java.util.Calendar;

public class AddStudentActivity extends AppCompatActivity {


    private static final String TAG = "AddStudentActivity";
    private EditText studentFirstname, studentMiddlename, studentLastname, studentAddress, studentPhone, studentEmail, studentBirthdate;
    private Spinner studentGender;
    private ImageView imgProfileStudent, btnBack;
    private Bitmap bitmap = null;
    private Button btnAdd;
    private StudentHandler studentHandler = new StudentHandler(this);
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private TextView test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        String school_id = getIntent().getStringExtra("school_id");

        btnBack = findViewById(R.id.backButton);
        btnAdd = findViewById(R.id.btnAddStudent);
        studentFirstname = findViewById(R.id.etFirstName);
        studentMiddlename = findViewById(R.id.etMiddleName);
        studentLastname = findViewById(R.id.etLastName);
        studentAddress = findViewById(R.id.etAddress);
        studentPhone = findViewById(R.id.etPhone);
        studentEmail = findViewById(R.id.etEmail);
        studentBirthdate = findViewById(R.id.etBirthDate);
        studentGender = findViewById(R.id.spinnerGender);
        imgProfileStudent = findViewById(R.id.imgStudentProfile);
        test = findViewById(R.id.tvTitle);





        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(AddStudentActivity.this, SchoolInfoActivity.class);
            startActivity(intent);
            finish();
        });

        btnAdd.setOnClickListener(v -> {
            String firstname = studentFirstname.getText().toString();
            String middlename = studentMiddlename.getText().toString();
            String lastname = studentLastname.getText().toString();
            String address = studentAddress.getText().toString();
            String phone = studentPhone.getText().toString();
            String email = studentEmail.getText().toString();
            String birthdate = studentBirthdate.getText().toString();
            String gender = studentGender.getSelectedItem().toString();

            if (bitmap == null){
                Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                String status = studentHandler.addStudent(bitmap, firstname, middlename, lastname,
                        address, phone, email, birthdate,
                        gender, school_id);

                runOnUiThread(() -> {
                    if (status.startsWith("success")) {
                        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed: " + status, Toast.LENGTH_LONG).show();
                    }
                });
            }).start();






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
                                imgProfileStudent.setImageBitmap(bitmap);
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

        studentBirthdate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddStudentActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String date = String.format("%04d-%02d-%02d",
                                selectedYear, (selectedMonth + 1), selectedDay);

                        studentBirthdate.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });



        findViewById(R.id.btnAddProfile).setOnClickListener(v -> openGallery());




    }
    private void openGallery() {
        Log.d(TAG, "Opening gallery...");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}