package com.gajanan.vaccinationinfoapp;

public class VaccinationModel {

    String centerId;
    String centerName;
    String centerAddress;
    String pinCode;
    String fromTime;
    String date;
    String toTime;
    String feeType;
    String fee;
    String minAge;
    String vaccineName;

    public VaccinationModel() {
    }

    public VaccinationModel(String centerId, String centerName, String centerAddress, String pinCode, String date,String fromTime, String toTime, String feeType, String fee, String minAge, String vaccineName) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.pinCode = pinCode;
        this.fromTime = fromTime;
        this.date = date;
        this.toTime = toTime;
        this.feeType = feeType;
        this.fee = fee;
        this.minAge = minAge;
        this.vaccineName = vaccineName;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
}
