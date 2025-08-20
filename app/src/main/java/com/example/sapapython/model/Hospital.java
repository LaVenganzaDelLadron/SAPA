package com.example.sapapython.model;

public class Hospital {
    String HospitalName;
    String HospitalDescription;
    String imageBase64;

    public Hospital(String hospitalName, String hospitalDescription, String imageBase64) {
        this.HospitalName = hospitalName;
        this.HospitalDescription = hospitalDescription;
        this.imageBase64 = imageBase64;
    }

    public String getHospitalName() {
        return HospitalName;
    }
    public String getHospitalDescription() {
        return HospitalDescription;
    }
    public String getImageBase64(){return imageBase64;}
}
