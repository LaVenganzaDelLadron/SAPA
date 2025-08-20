package com.example.sapapython.model;

public class Post {
    private String caption;
    private String imageBase64;

    public Post(String caption, String imageBase64) {
        this.caption = caption;
        this.imageBase64 = imageBase64;
    }

    public String getCaption() {
        return caption;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
