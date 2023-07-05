package com.example.hackathon;

public class ReportRequest {
    private String place;

    private String latitude;

    private String longitude;

    private String description;

    public ReportRequest(String place, String latitude, String longitude, String description){
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }
}
