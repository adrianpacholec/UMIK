package com.example.adrian.umik;

public class UserUpdate {
    private String userId;
    private double latitude;
    private double longitude;
    private long timestamp;
    public UserUpdate(String user, double lat, double lon){
        userId = user;
        latitude = lat;
        longitude = lon;
        timestamp = System.currentTimeMillis()/1000;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
