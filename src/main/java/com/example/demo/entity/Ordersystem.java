package com.example.demo.entity;

public class Ordersystem {
    private int id;
    private String userid;
    private double powernumber;
    private double payrank;
    private String sdate;
    private int status;
    private String ordernumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public double getPowernumber() {
        return powernumber;
    }

    public void setPowernumber(double powernumber) {
        this.powernumber = powernumber;
    }

    public double getPaybank() {
        return payrank;
    }

    public void setPaybank(double paybank) {
        this.payrank = paybank;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}
