package com.mongodbconnection.demo.Model.FaceValues;

public class FaceAttributes {
    public FaceAttributes(double age, String gender, double smile) {
        this.age = age;
        this.gender = gender;
        this.smile = smile;
    }

    private double age;
    private String gender;
    private double smile;

    public FaceAttributes() {
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSmile() {
        return smile;
    }

    public void setSmile(double smile) {
        this.smile = smile;
    }

}
