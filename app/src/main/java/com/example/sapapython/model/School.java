package com.example.sapapython.model;

import com.example.sapapython.SchoolActivity;

public class School {
    String schoolId;
    String schoolName;
    String schoolNumber;
    String schoolCode;
    String schoolAddress;
    String schoolBio;
    String imageBase64;

    public School(String schoolId, String schoolName, String schoolNumber, String schoolCode, String schoolAddress, String schoolBio, String imageBase64) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolNumber = schoolNumber;
        this.schoolCode = schoolCode;
        this.schoolAddress = schoolAddress;
        this.schoolBio = schoolBio;
        this.imageBase64 = imageBase64;
    }

    public School(String schoolId,String schoolName, String schoolAddress, String imageBase64) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.imageBase64 = imageBase64;
    }


    public String getSchoolId() {
        return schoolId;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public String getSchoolBio() {
        return schoolBio;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
