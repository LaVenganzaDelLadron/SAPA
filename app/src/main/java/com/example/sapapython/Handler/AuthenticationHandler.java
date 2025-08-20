package com.example.sapapython.Handler;

import android.content.Context;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class AuthenticationHandler {

    private Context context;

    public AuthenticationHandler(Context context){
        this.context = context;
    }


    public String Signup(String email, String firstname, String middlename, String lastname, String password){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("Authentication");
        PyObject result = pyModule.callAttr("signup", email, firstname, middlename, lastname, password);

        return result.toString().trim();
    }

    public String Login(String email, String password){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("Authentication");
        PyObject result = pyModule.callAttr("login", email, password);

        return result.toString().trim();
    }


}
