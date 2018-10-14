package com.mazotapp.mazotapp;

public class StationModel {

    private int stationLogo,stPhoto;
    private double stPositionX,stPositionY;
    private String stationName,stationPrGasoline,stationInfo;

    public StationModel(int stationLogo,int stPhoto, String stationName, String stationPrGasoline, String stationInfo,double stPositionX,double stPositionY){

        this.stationLogo = stationLogo;
        this.stPhoto = stPhoto;
        this.stationName = stationName;
        this.stationPrGasoline = stationPrGasoline;
        this.stationInfo = stationInfo;
        this.stPositionX = stPositionX;
        this.stPositionY = stPositionY;

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

    public String getStationPrGasoline() {
        return stationPrGasoline;
    }

    public void setStationPrGasoline(String stationPrGasoline) {
        this.stationPrGasoline = stationPrGasoline;
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo;
    }
}
