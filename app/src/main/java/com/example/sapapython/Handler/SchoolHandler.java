package com.example.sapapython.Handler;

import android.content.Context;
import android.graphics.Bitmap;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.sapapython.model.School;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SchoolHandler {
    private Context context;

    public SchoolHandler(Context context) {
        this.context = context;
    }


    public String getSchoolIndex(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getSchoolId");
        String status = result.toString();

        return status;
    }

    public String getSchoolName(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getSchoolName");
        String status = result.toString();

        return status;
    }


    public String getSchoolAddress(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getSchoolAddress");
        String status = result.toString();

        return status;
    }


    public String getSchoolNumber(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getSchoolNumber");
        String status = result.toString();

        return status;
    }

    public String getSchoolCode(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getSchoolCode");
        String status = result.toString();

        return status;
    }


    public String getSchoolBio(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("connectDB");
        PyObject result = pyModule.callAttr("getSchoolBio");
        String status = result.toString();

        return status;
    }

    public String addSchool(Bitmap school_profile, String school_name, String school_number, String school_code, String school_address, String school_bio, String school_coordinator_id) {
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("School");

        int coordinatorId = Integer.parseInt(school_coordinator_id);

        byte[] profileBytes = null;
        if (school_profile != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            school_profile.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            profileBytes = stream.toByteArray();
        }
        PyObject result = pyModule.callAttr("addSchool", profileBytes, school_name, school_number, school_code, school_address, school_bio, coordinatorId);

        return result.toString();
    }


    public List<School> getSchool(String index){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("School");
        PyObject result = pyModule.callAttr("getAllSchool", index);

        List<School> schoolList = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(result.toString());
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                String school_id = obj.optString("school_id");
                String school_name = obj.optString("school_name");
                String school_number = obj.optString("school_number");
                String school_code = obj.optString("school_code");
                String school_address = obj.optString("school_address");
                String school_bio = obj.optString("school_bio");
                String imageBase64 = obj.optString("imageBase64", null);

                schoolList.add(new School(school_id, school_name, school_number, school_code, school_address, school_bio, imageBase64));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return schoolList;
    }




}

