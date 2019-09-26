package com.example.mobilebusticketingsystem.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {
    @SerializedName("busPlateNo")
    @Expose
    private String busPlateNo;
    @SerializedName("busNo")
    @Expose
    private String busNo;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;

    private String time;


    public Bus(String startTime, String endTime, String time) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.time = time;

    }

    public Bus(String busPlateNo, String busNo, float price, String startTime, String endTime) {
        this.busPlateNo = busPlateNo;
        this.busNo = busNo;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBusPlateNo() {
        return busPlateNo;
    }

    public void setBusPlateNo(String busPlateNo) {
        this.busPlateNo = busPlateNo;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
