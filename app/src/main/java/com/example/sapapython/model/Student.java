package com.example.sapapython.model;

public class Student {
    private String student_id;
    private String student_firstname;
    private String student_middlename;
    private String student_lastname;
    private String student_address;
    private String phone_number;
    private String student_email;
    private String student_birthdate;
    private String student_gender;
    private String imageBase64;

    public Student(String student_id, String student_firstname, String student_middlename,
                   String student_lastname, String student_address, String phone_number,
                   String student_email, String student_birthdate, String student_gender,
                   String imageBase64) {
        this.student_id = student_id;
        this.student_firstname = student_firstname;
        this.student_middlename = student_middlename;
        this.student_lastname = student_lastname;
        this.student_address = student_address;
        this.phone_number = phone_number;
        this.student_email = student_email;
        this.student_birthdate = student_birthdate;
        this.student_gender = student_gender;
        this.imageBase64 = imageBase64;
    }

    // Getters
    public String getStudent_id() { return student_id; }
    public String getStudent_firstname() { return student_firstname; }
    public String getStudent_middlename() { return student_middlename; }
    public String getStudent_lastname() { return student_lastname; }
    public String getStudent_address() { return student_address; }
    public String getPhone_number() { return phone_number; }
    public String getStudent_email() { return student_email; }
    public String getStudent_birthdate() { return student_birthdate; }
    public String getStudent_gender() { return student_gender; }
    public String getImageBase64() { return imageBase64; }
}
