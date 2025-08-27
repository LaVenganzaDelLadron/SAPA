package com.example.sapapython.Handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class ProfileHandler {
    private Context context;

    public ProfileHandler(Context context){
        this.context = context;
    }

    public String updateProfile(String school_coordinator_id, Bitmap school_coordinator_profile, String email, String firstname, String middlename, String lastname, String phone_number, String birthdate, String address, String gender, String bio){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("Profile");

        int coordinatorId = Integer.parseInt(school_coordinator_id);

        byte[] profileBytes = null;
        if (school_coordinator_profile != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            school_coordinator_profile.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            profileBytes = stream.toByteArray();
        }
        PyObject result = pyModule.callAttr("updateCoordinator", coordinatorId, profileBytes, email, firstname, middlename, lastname, phone_number, birthdate, address, gender, bio);

        return result.toString();
    }


    public Bitmap getProfileImage(String index) {
        byte[] imageBytes = null;

        try {
            if (!Python.isStarted()) {
                Python.start(new AndroidPlatform(context));
            }
            Python py = Python.getInstance();
            PyObject pyModule = py.getModule("Profile");
            PyObject result = pyModule.callAttr("getCoordinatorProfileImage", index);
            imageBytes = result.toJava(byte[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imageBytes != null && imageBytes.length > 0) {
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } else {
            return null;
        }
    }


}
