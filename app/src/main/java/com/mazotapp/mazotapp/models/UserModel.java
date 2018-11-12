package com.mazotapp.mazotapp.models;

public class UserModel {

    private String name,surname,phoneNumber,email,password,cityName;
    private int cbLowPrice,cbToilet,cbNear,rdGasoline,rdLPG,rdDiesel;
    Double locationLongitude,locationLatitude;


    public UserModel( ) {
    }

    public UserModel(String name, String surname, String phoneNumber, String email, String password, int cbLowPrice, int cbToilet, int cbNear, int rdGasoline, int rdLPG, int rdDiesel, Double locationLongitude,Double locationLatitude,String cityName) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.cbLowPrice = cbLowPrice;
        this.cbToilet = cbToilet;
        this.cbNear = cbNear;
        this.rdGasoline = rdGasoline;
        this.rdLPG = rdLPG;
        this.rdDiesel = rdDiesel;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public int getCbLowPrice() {
        return cbLowPrice;
    }

    public void setCbLowPrice(int cbLowPrice) {
        this.cbLowPrice = cbLowPrice;
    }

    public int getCbToilet() {
        return cbToilet;
    }

    public void setCbToilet(int cbToilet) {
        this.cbToilet = cbToilet;
    }

    public int getCbNear() {
        return cbNear;
    }

    public void setCbNear(int cbNear) {
        this.cbNear = cbNear;
    }

    public int getRdGasoline() {
        return rdGasoline;
    }

    public void setRdGasoline(int rdGasoline) {
        this.rdGasoline = rdGasoline;
    }

    public int getRdLPG() {
        return rdLPG;
    }

    public void setRdLPG(int rdLPG) {
        this.rdLPG = rdLPG;
    }

    public int getRdDiesel() {
        return rdDiesel;
    }

    public void setRdDiesel(int rdDiesel) {
        this.rdDiesel = rdDiesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
