package com.example.sapapython;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.Handler.ConnectionHandler;
import com.example.sapapython.adapter.PostAdapter;
import com.example.sapapython.Handler.PostHandler;
import com.example.sapapython.model.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private Button btnHospital, btnSchool, btnStudent, btnNotification, btnChat, btnBill, btnAppointment, btnSetting;
    private TextView fullNameView;
    private ConnectionHandler connectionHandler = new ConnectionHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnHospital = findViewById(R.id.btnHospital);
        btnSchool = findViewById(R.id.btnSchool);
        btnStudent = findViewById(R.id.btnStudent);
        btnNotification = findViewById(R.id.btnNotification);
        btnChat = findViewById(R.id.btnChat);
        btnBill = findViewById(R.id.btnBill);
        btnAppointment = findViewById(R.id.btnAppointment);
        btnSetting = findViewById(R.id.btnSettings);
        fullNameView = findViewById(R.id.fullNameView);



        btnHospital.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, HospitalActivity.class);
            startActivity(intent);
        });

        btnSchool.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, SchoolActivity.class);
            startActivity(intent);
        });

        String firstname = connectionHandler.getFirstname();
        String middlename = connectionHandler.getMiddlename();
        String lastname = connectionHandler.getLastname();

        fullNameView.setText(firstname + " " + middlename + " " + lastname);


        recyclerView = findViewById(R.id.recycleViewPost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PostHandler postHandler = new PostHandler(this);
        postList = postHandler.getPosts();

        postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);
    }
}
