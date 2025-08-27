package com.example.sapapython.model;

public class Hospital {
    String firstName, middleName, lastName;
    String imageBase64;

    public Hospital(String firstName, String middleName, String lastName, String imageBase64) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.imageBase64 = imageBase64;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
