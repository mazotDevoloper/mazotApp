package com.mazotapp.mazotapp.models;

public class StationModel {

    private double stPositionX,stPositionY,stationPrice,stDiesel,stLPG,stGasoline;
    private String stationName,stationInfo,stationLogo,stPhoto;
    private int stCleanToilet;

    public StationModel(String stationLogo,String stPhoto, String stationName,int stCleanToilet, double stationPrice,double stDiesel,double stGasoline,double stLPG, String stationInfo,double stPositionX,double stPositionY){

        this.stCleanToilet = stCleanToilet;
        this.stationLogo = stationLogo;
        this.stPhoto = stPhoto;
        this.stationName = stationName;
        this.stationPrice = stationPrice;
        this.stationInfo = stationInfo;
        this.stPositionX = stPositionX;
        this.stPositionY = stPositionY;
        this.stDiesel = stDiesel;
        this.stGasoline = stGasoline;
        this.stLPG = stLPG;
    }

    public StationModel(){}

    public int getStCleanToilet() {
        return stCleanToilet;
    }

    public void setStCleanToilet(int stCleanToilet) {
        this.stCleanToilet = stCleanToilet;
    }

    public String getStationLogo() {
        return stationLogo;
    }

    public void setStationLogo(String stationLogo) {
        this.stationLogo = stationLogo;
    }

    public String getStPhoto() {
        return stPhoto;
    }

    public void setStPhoto(String stPhoto) {
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

    public double getStDiesel() {
        return stDiesel;
    }

    public void setStDiesel(double stDiesel) {
        this.stDiesel = stDiesel;
    }

    public double getStLPG() {
        return stLPG;
    }

    public void setStLPG(double stLPG) {
        this.stLPG = stLPG;
    }

    public double getStGasoline() {
        return stGasoline;
    }

    public void setStGasoline(double stGasoline) {
        this.stGasoline = stGasoline;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getStationPrice() {
        return stationPrice;
    }

    public void setStationPrice(double stationPrice) {
        this.stationPrice = stationPrice;
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo;
    }
}
