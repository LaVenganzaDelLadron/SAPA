package com.example.sapapython;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.Handler.ConnectionHandler;
import com.example.sapapython.Handler.SchoolHandler;
import com.example.sapapython.adapter.SchoolAdapter;
import com.example.sapapython.model.School;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class    SchoolActivity extends AppCompatActivity {

    private FloatingActionButton addSchool;
    private ImageView btnBack;
    private RecyclerView recyclerView;
    private List<School> schoolList;
    private SchoolAdapter schoolAdapter;
    private ConnectionHandler connectionHandler = new ConnectionHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_school);

        addSchool = findViewById(R.id.fabAddSchool);
        btnBack = findViewById(R.id.backButton);
        recyclerView = findViewById(R.id.recycleViewSchool);


        addSchool.setOnClickListener(v -> {
            Intent intent = new Intent(SchoolActivity.this, AddSchoolActivity.class);
            startActivity(intent);
            finish();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SchoolActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SchoolHandler schoolHandler = new SchoolHandler(this);
        schoolList = schoolHandler.getSchool(connectionHandler.getIndex());

        schoolAdapter = new SchoolAdapter(this, schoolList);
        recyclerView.setAdapter(schoolAdapter);
    }
}