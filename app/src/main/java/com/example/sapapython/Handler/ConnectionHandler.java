package com.example.sapapython.Handler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class ConnectionHandler {

    private Context context;

    public ConnectionHandler(Context context) {
        this.context = context;
    }

    public String ConnectionDB() {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("connect");
        String status = result.toString();

        if(status.equals("success")){
            Toast.makeText(context, "Successfully Connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Failed to Connect", Toast.LENGTH_SHORT).show();
        }

        return status;
    }



}

