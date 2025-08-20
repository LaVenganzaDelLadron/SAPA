package com.example.sapapython;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.Handler.HospitalHandler;
import com.example.sapapython.adapter.HospitalAdapter;
import com.example.sapapython.model.Hospital;

import java.util.List;

public class HospitalActivity extends AppCompatActivity {

    ImageView btnBack;
    RecyclerView recyclerView;
    HospitalAdapter hospitalAdapter;
    List<Hospital> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        btnBack = findViewById(R.id.backButton);
        recyclerView = findViewById(R.id.recycleViewHospital);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(HospitalActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HospitalHandler hospitalHandler = new HospitalHandler(this);
        hospitalList = hospitalHandler.getHospital();

        hospitalAdapter = new HospitalAdapter(this, hospitalList);
        recyclerView.setAdapter(hospitalAdapter);
    }
}
