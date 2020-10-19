package com.medicto.medicto.model;

public class PharmacyModel {

    String pharCode;
    String pharName;
    String pharAddress;
    String pharZipCode;
    String pharPhoneNumber;
    String latitude ;
    String longitude;
    String startDate;
    String engName;


    public PharmacyModel(String pharCode,String pharName,String pharAddress,String pharZipCode, String pharPhoneNumber, String latitude,String longitude, String startDate,String engName){

        this.pharCode = pharCode;
        this.pharName = pharName;
        this.pharAddress = pharAddress;
        this.pharZipCode = pharZipCode;
        this.pharPhoneNumber = pharPhoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.engName = engName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getPharCode() {
        return pharCode;
    }

    public void setPharCode(String pharCode) {
        this.pharCode = pharCode;
    }

    public String getPharName() {
        return pharName;
    }

    public void setPharName(String pharName) {
        this.pharName = pharName;
    }

    public String getPharAddress() {
        return pharAddress;
    }

    public void setPharAddress(String pharAddress) {
        this.pharAddress = pharAddress;
    }

    public String getPharZipCode() {
        return pharZipCode;
    }

    public void setPharZipCode(String pharZipCode) {
        this.pharZipCode = pharZipCode;
    }

    public String getPharPhoneNumber() {
        return pharPhoneNumber;
    }

    public void setPharPhoneNumber(String pharPhoneNumber) {
        this.pharPhoneNumber = pharPhoneNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
