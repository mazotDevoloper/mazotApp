package com.mazotapp.mazotapp.models;

public class StationListModel {

    private double distance;
    private String price,stationName,stationId,stationLogoURL;

    public StationListModel(double distance,String price,String stationName,String stationId,String stationLogoURL){

        this.distance = distance;
        this.price = price;
        this.stationName = stationName;
        this.stationId = stationId;
        this.stationLogoURL = stationLogoURL;

    }

    public String getStationLogoURL() {
        return stationLogoURL;
    }

    public void setStationLogoURL(String stationLogoURL) {
        this.stationLogoURL = stationLogoURL;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
