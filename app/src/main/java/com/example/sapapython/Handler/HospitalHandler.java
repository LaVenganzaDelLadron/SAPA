package com.example.sapapython.Handler;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.sapapython.model.Hospital;
import com.example.sapapython.model.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HospitalHandler {
    private Context context;

    public HospitalHandler(Context context){
        this.context = context;
    }

    public List<Hospital> getHospital(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("Hospital");
        PyObject result = pyModule.callAttr("getAllHospital");

        List<Hospital> hospitalList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String firstname = obj.optString("firstname");
                String middlename = obj.optString("middlename");
                String lastname = obj.optString("lastname");
                String imageBase64 = obj.optString("imageBase64", null);

                hospitalList.add(new Hospital(firstname, middlename, lastname, imageBase64));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospitalList;
    }

}
