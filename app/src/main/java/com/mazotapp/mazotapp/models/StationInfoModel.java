package com.mazotapp.mazotapp.models;

public class StationInfoModel {

    private int stPhoto;
    private double stPositionX,stPositionY;
    private String stationName,stationPrice,stationInfo;

    public StationInfoModel(int stPhoto, double stPositionX, double stPositionY, String stationName, String stationPrice, String stationInfo) {
        this.stPhoto = stPhoto;
        this.stPositionX = stPositionX;
        this.stPositionY = stPositionY;
        this.stationName = stationName;
        this.stationPrice = stationPrice;
        this.stationInfo = stationInfo;
    }

    public int getStPhoto() {
        return stPhoto;
    }

    public void setStPhoto(int stPhoto) {
        this.stPhoto = stPhoto;
    }

    public double getStPositionX() {
        return stPositionX;
    }

    public void setStPositionX(double stPositionX) {
        this.stPositionX = stPositionX;
    }

    public double getStPositionY() {
        return stPositionY;
    }

    public void setStPositionY(double stPositionY) {
        this.stPositionY = stPositionY;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationPrice() {
        return stationPrice;
    }

    public void setStationPrice(String stationPrice) {
        this.stationPrice = stationPrice;
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo;
    }
}
