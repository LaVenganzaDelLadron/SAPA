package com.example.sapapython.Handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.sapapython.model.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentHandler {
    private Context context;
    private static final String TAG = "StudentHandler";

    public StudentHandler(Context context){
        this.context = context;
    }

    public String addStudent(Bitmap student_profile, String student_firstname, String student_middlename, String student_lastname,
                             String student_address, String phone_number, String student_email,
                             String student_birthdate, String student_gender, String school_id) {
        try {
            if (!Python.isStarted()) {
                Python.start(new AndroidPlatform(context));
            }
            Python py = Python.getInstance();
            PyObject pyModule = py.getModule("Student");

            int schoolId = Integer.parseInt(school_id);

            byte[] profileBytes = null;
            if (student_profile != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                student_profile.compress(Bitmap.CompressFormat.JPEG, 90, stream);
                profileBytes = stream.toByteArray();
                Log.d(TAG, "Profile image converted to byte array, size: " + profileBytes.length);
            } else {
                Log.d(TAG, "No profile image provided.");
            }

            Log.d(TAG, "Adding student: " + student_firstname + " " + student_lastname + ", School ID: " + schoolId);

            PyObject result = pyModule.callAttr("addStudent", profileBytes, student_firstname, student_middlename,
                    student_lastname, student_address, phone_number, student_email,
                    student_birthdate, student_gender, schoolId);

            Log.d(TAG, "Python addStudent result: " + result.toString());
            return result.toString();

        } catch (Exception e) {
            Log.e(TAG, "Error in addStudent: " + e.getMessage(), e);
            return "Error: " + e.getMessage();
        }
    }

    public List<Student> getStudent(String index){
        List<Student> studentList = new ArrayList<>();

        /*studentList.add(new Student("1", "Juan", "S.", "Dela Cruz", "Makati City", ""));
        studentList.add(new Student("2", "Maria", "L.", "Santos", "Quezon City", ""));
        studentList.add(new Student("3", "Pedro", "G.", "Reyes", "Pasig City", ""));*/

        try {
            if (!Python.isStarted()) {
                Python.start(new AndroidPlatform(context));
            }

            Python py = Python.getInstance();
            PyObject pyModule = py.getModule("Student");

            PyObject result = pyModule.callAttr("getAllStudent", index);

            JSONArray jsonArray = new JSONArray(result.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String student_id = obj.optString("student_id");
                String student_firstname = obj.optString("student_firstname");
                String student_middlename = obj.optString("student_middlename");
                String student_lastname = obj.optString("student_lastname");
                String student_address = obj.optString("student_address");
                String phone_number = obj.optString("phone_number");
                String student_email = obj.optString("student_email");
                String student_birthdate = obj.optString("student_birthdate");
                String student_gender = obj.optString("student_gender");
                String imageBase64 = obj.optString("imageBase64", null);


                studentList.add(new Student(student_id, student_firstname, student_middlename, student_lastname,
                        student_address, phone_number, student_email, student_birthdate, student_gender, imageBase64));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getStudent: " + e.getMessage(), e);
        }
        return studentList;
    }
}
