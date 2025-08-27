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


    public String getIndex(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getId");
        String status = result.toString();

        return status;
    }

    public String getEmail(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getEmail");
        String status = result.toString();

        return status;
    }
    public String getFirstname(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
    
        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getFirstname");
        String status = result.toString();

        return status;
    }

    public String getMiddlename(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getMiddlename");
        String status = result.toString();

        return status;
    }

    public String getLastname(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getLastname");
        String status = result.toString();

        return status;
    }

    public String getPhone(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getPhone");
        String status = result.toString();

        return status;
    }

    public String getBirthdate(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getBirthdate");
        String status = result.toString();

        return status;
    }

    public String getAddress(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getAddress");
        String status = result.toString();

        return status;
    }

    public String getGender(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getGender");
        String status = result.toString();

        return status;
    }

    public String getBio(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getBio");
        String status = result.toString();

        return status;
    }


}

