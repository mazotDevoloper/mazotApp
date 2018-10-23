package com.mazotapp.mazotapp.models;

public class StationModel {

    private int stationLogo,stPhoto;
    private double stPositionX,stPositionY;
    private String stationName,stationPrice,stationInfo,stDistance;

    public StationModel(int stationLogo,int stPhoto, String stationName, String stationPrice,String stDistance, String stationInfo,double stPositionX,double stPositionY){

        this.stationLogo = stationLogo;
        this.stPhoto = stPhoto;
        this.stationName = stationName;
        this.stationPrice = stationPrice;
        this.stationInfo = stationInfo;
        this.stPositionX = stPositionX;
        this.stPositionY = stPositionY;
        this.stDistance = stDistance;

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

    public int getStationLogo() {
        return stationLogo;
    }

    public void setStationLogo(int stationLogo) {
        this.stationLogo = stationLogo;
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

    public String getStDistance() {
        return stDistance;
    }

    public void setStDistance(String stDistance) {
        this.stDistance = stDistance;
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo;
    }
}
