package com.example.sapapython;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.Handler.StudentHandler;
import com.example.sapapython.adapter.StudentAdapter;
import com.example.sapapython.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SchoolInfoActivity extends AppCompatActivity {

    private ImageView schoolProfile;
    private RecyclerView recyclerView;
    private TextView schoolName, schoolAddress, schoolBio, schoolNumber, schoolCode;
    private FloatingActionButton fabSchool;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_school_info);

        String school_id = getIntent().getStringExtra("school_id");
        String school_name = getIntent().getStringExtra("school_name");
        String school_number = getIntent().getStringExtra("school_number");
        String school_code = getIntent().getStringExtra("school_code");
        String school_address = getIntent().getStringExtra("school_address");
        String school_bio = getIntent().getStringExtra("school_bio");

        schoolName = findViewById(R.id.detailSchoolName);
        schoolAddress = findViewById(R.id.detailSchoolAddress);
        schoolBio = findViewById(R.id.detailSchoolBio);
        schoolNumber = findViewById(R.id.detailSchoolPhone);
        schoolCode = findViewById(R.id.detailSchoolZip);
        fabSchool = findViewById(R.id.fabAddSchool);
        recyclerView = findViewById(R.id.recyclerViewStudent);

        schoolName.setText(school_name);
        schoolNumber.setText(school_number);
        schoolCode.setText(school_code);
        schoolAddress.setText(school_address);
        schoolBio.setText(school_bio);

        fabSchool.setOnClickListener(v -> {
            Intent intent = new Intent(SchoolInfoActivity.this, AddStudentActivity.class);
            intent.putExtra("school_id", school_id);
            startActivity(intent);
            finish();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentHandler studentHandler = new StudentHandler(this);
        studentList = studentHandler.getStudent(school_id);

        studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);
    }
}
