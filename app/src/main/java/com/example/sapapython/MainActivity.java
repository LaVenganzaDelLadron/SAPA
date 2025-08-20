package com.example.sapapython;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.sapapython.Handler.ConnectionHandler;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView loadingText;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        loadingText = findViewById(R.id.loadingText);


        ConnectionHandler connectionHandler = new ConnectionHandler(this);
        String status = connectionHandler.ConnectionDB();


        new Thread(() -> {
            if(status.equals("success")){
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }else{
                loadingText.setText("Connecting...");
            }

        }).start();
    }
}
