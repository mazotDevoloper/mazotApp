package com.mazotapp.mazotapp.models;

public class StationModel {

    private double stPositionX,stPositionY;
    private String stationName,stationLogo,stPhoto,stBrand;
    private Boolean stMarket,stCafe,stCarwash,stOil,stWc,st24hour,stMigros,stMescit,stATM,stTire,stFood,stRedBull;

    public StationModel(String stationLogo,String stPhoto, String stationName,double stPositionX,double stPositionY,String stBrand,Boolean st24hour, Boolean stCafe,Boolean stCarwash,Boolean stOil,Boolean stWc,Boolean stMarket,Boolean stTire, Boolean stMigros, Boolean stMescit, Boolean stATM,Boolean stRedBull,Boolean stFood){

        this.st24hour = st24hour;
        this.stCarwash = stCarwash;
        this.stCafe = stCafe;
        this.stOil = stOil;
        this.stMarket = stMarket;
        this.stWc = stWc;
        this.stATM = stATM;
        this.stMescit = stMescit;
        this.stMigros = stMigros;
        this.stTire = stTire;
        this.stBrand = stBrand;
        this.stationLogo = stationLogo;
        this.stPhoto = stPhoto;
        this.stationName = stationName;
        this.stPositionX = stPositionX;
        this.stPositionY = stPositionY;
        this.stFood = stFood;
        this.stRedBull = stRedBull;
    }

    public StationModel(){}

    public Boolean getStFood() {
        return stFood;
    }

    public void setStFood(Boolean stFood) {
        this.stFood = stFood;
    }

    public Boolean getStRedBull() {
        return stRedBull;
    }

    public void setStRedBull(Boolean stRedBull) {
        this.stRedBull = stRedBull;
    }

    public Boolean getStMigros() {
        return stMigros;
    }

    public void setStMigros(Boolean stMigros) {
        this.stMigros = stMigros;
    }

    public Boolean getStMescit() {
        return stMescit;
    }

    public void setStMescit(Boolean stMescit) {
        this.stMescit = stMescit;
    }

    public Boolean getStATM() {
        return stATM;
    }

    public void setStATM(Boolean stATM) {
        this.stATM = stATM;
    }

    public Boolean getStTire() {
        return stTire;
    }

    public void setStTire(Boolean stTire) {
        this.stTire = stTire;
    }

    public Boolean getStMarket() {
        return stMarket;
    }

    public void setStMarket(Boolean stMarket) {
        this.stMarket = stMarket;
    }

    public Boolean getStCafe() {
        return stCafe;
    }

    public void setStCafe(Boolean stCafe) {
        this.stCafe = stCafe;
    }

    public Boolean getStCarwash() {
        return stCarwash;
    }

    public void setStCarwash(Boolean stCarwash) {
        this.stCarwash = stCarwash;
    }

    public Boolean getStOil() {
        return stOil;
    }

    public void setStOil(Boolean stOil) {
        this.stOil = stOil;
    }

    public Boolean getStWc() {
        return stWc;
    }

    public void setStWc(Boolean stWc) {
        this.stWc = stWc;
    }

    public Boolean getSt24hour() {
        return st24hour;
    }

    public void setSt24hour(Boolean st24hour) {
        this.st24hour = st24hour;
    }

    public String getStBrand() {
        return stBrand;
    }

    public void setStBrand(String stBrand) {
        this.stBrand = stBrand;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
