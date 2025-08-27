package com.example.sapapython;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.Handler.HospitalHandler;
import com.example.sapapython.Handler.StudentHandler;
import com.example.sapapython.adapter.AllStudentAdapter;
import com.example.sapapython.adapter.HospitalAdapter;
import com.example.sapapython.model.Hospital;
import com.example.sapapython.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private ImageView btnBack;
    private RecyclerView recyclerView;
    private AllStudentAdapter allStudentAdapter;
    private List<Student> studentList;
    private FloatingActionButton fabAddSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);

        recyclerView = findViewById(R.id.recycleViewAllStudent);
        fabAddSchool = findViewById(R.id.fabAddSchool);
        btnBack = findViewById(R.id.backButton);


        fabAddSchool.setOnClickListener(v -> {
            Intent intent = new Intent(StudentActivity.this, SchoolActivity.class);
            startActivity(intent);
            finish();
        });


        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(StudentActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentHandler studentHandler = new StudentHandler(this);
        studentList = studentHandler.getStudentInEverySchool();

        allStudentAdapter = new AllStudentAdapter(this, studentList);
        recyclerView.setAdapter(allStudentAdapter);

    }
}