package com.example.hackathon;

public class ReportResponse {
    private Long reportId;
    private String reporter;
    private String place;
    private Double latitude;
    private Double longitude;
    private Boolean isCompleted;



    public Long getReportId() {
        return reportId;
    }

    public String getReporter(){
        return reporter;
    }

    public String getPlace() {
        return place;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

}
