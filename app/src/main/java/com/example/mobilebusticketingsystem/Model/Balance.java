package com.example.mobilebusticketingsystem.Model;

public class Balance {

    private String _id;
    private float balance;
    private boolean active;
    private String created;


    public Balance(String _id, float balance, boolean active, String created) {
        this._id = _id;
        this.balance = balance;
        this.active = active;
        this.created = created;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}

