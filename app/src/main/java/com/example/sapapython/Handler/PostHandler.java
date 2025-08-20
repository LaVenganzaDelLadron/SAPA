package com.example.sapapython.Handler;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.sapapython.model.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostHandler {

    private Context context;

    public PostHandler(Context context){
        this.context = context;
    }

    public List<Post> getPosts(){
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }

        Python py = Python.getInstance();
        PyObject pyModule = py.getModule("Post");
        PyObject result = pyModule.callAttr("getAllPost");

        List<Post> postList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String caption = obj.optString("caption");
                String imageBase64 = obj.optString("imageBase64", null);

                postList.add(new Post(caption, imageBase64));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postList;
    }
}
