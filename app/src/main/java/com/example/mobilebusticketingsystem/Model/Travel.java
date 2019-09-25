package com.example.mobilebusticketingsystem.Model;


public class Travel {

    private String _id;
    private String passengerId;
    private String busId;
    private String date;
    private String startingPoint;
    private String endingPoint;
    private float fare;
    private String timeDuration;

    public Travel(String _id, String passengerId, String busId, String date, String startingPoint, String endingPoint, float fare, String timeDuration) {
        this._id = _id;
        this.passengerId = passengerId;
        this.busId = busId;
        this.date = date;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.fare = fare;
        this.timeDuration = timeDuration;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

}
