package com.mongodbconnection.demo.Model.FaceValues;

public class FaceValues {

    private String faceId;
    private FaceRectangle faceRectangle;
    private FaceLandmarks faceLandmarks;
    private FaceAttributes faceAttributes;

    public FaceValues() {
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public FaceRectangle getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(FaceRectangle faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public FaceLandmarks getFaceLandmarks() {
        return faceLandmarks;
    }

    public void setFaceLandmarks(FaceLandmarks faceLandmarks) {
        this.faceLandmarks = faceLandmarks;
    }

    public FaceAttributes getFaceAttributes() {
        return faceAttributes;
    }

    public void setFaceAttributes(FaceAttributes faceAttributes) {
        this.faceAttributes = faceAttributes;
    }

    public FaceValues(String faceId, FaceRectangle faceRectangle, FaceLandmarks faceLandmarks, FaceAttributes faceAttributes) {
        this.faceId = faceId;
        this.faceRectangle = faceRectangle;
        this.faceLandmarks = faceLandmarks;
        this.faceAttributes = faceAttributes;
    }



}
