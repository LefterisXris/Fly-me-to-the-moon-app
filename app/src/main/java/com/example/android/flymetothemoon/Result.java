package com.example.android.flymetothemoon;

/**
 * Created by tsita on 10/1/2017.
 */

//TODO delete this class. Δεν χρησιμοποιείται πουθενά.

public class Result {

    private int mAa;
    private String mCompany;
    private String mFlight_code;
    private String mDep_time;
    private String mArr_time;
    private double mPrice;


    public Result(String company, String flight_code, String dep_time, String arr_time, double price, int aa){
        mAa = aa;
        mCompany = company;
        mFlight_code = flight_code;
        mDep_time = dep_time;
        mArr_time = arr_time;
        mPrice = price;

    }


    public String getArr_time() {
        return mArr_time;
    }

    public void setArr_time(String mArr_time) {
        this.mArr_time = mArr_time;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getDep_time() {
        return mDep_time;
    }

    public void setDep_time(String mDep_time) {
        this.mDep_time = mDep_time;
    }

    public String getFlight_code() {
        return mFlight_code;
    }

    public void setFlight_code(String mFlight_code) {
        this.mFlight_code = mFlight_code;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public int getAa() {

        return mAa;
    }

    public void setAa(int mAa) {
        this.mAa = mAa;
    }
}
