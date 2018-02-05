package com.example.android.flymetothemoon;

import static com.example.android.flymetothemoon.R.string.origin;

/**
 * Created by tsita on 14/1/2017.
 */

public class FlightQuery {

    //region Fields

    private String mOrigin;
    private String mDestination;
    private String mDeparrture_date;
    private String mReturn_date;
    private String mNonstop;
    private String mMax_price;
    private String mMaxResults;
    private String mCurrency;

    private String oneWay;
    private String persons;

    //endregion

    public FlightQuery(String origin, String destination, String deparrture_date, String return_date, String nonstop, String max_price, String max_results,
                       String currency, String oneWay, String persons) {

        mOrigin = origin;
        mDestination = destination;
        mDeparrture_date = deparrture_date;
        mReturn_date = return_date;
        mNonstop = nonstop;

        mMax_price = max_price;
        mMaxResults = max_results;
        mCurrency = currency;
        this.oneWay = oneWay;
        this.persons = persons;

    }


    //region Getters & Setters

    public String getmOrigin() {
        return mOrigin;
    }

    public void setmOrigin(String mOrigin) {
        this.mOrigin = mOrigin;
    }

    public String getmDestination() {
        return mDestination;
    }

    public void setmDestination(String mDestination) {
        this.mDestination = mDestination;
    }

    public String getmDeparrture_date() {
        return mDeparrture_date;
    }

    public void setmDeparrture_date(String mDeparrture_date) {
        this.mDeparrture_date = mDeparrture_date;
    }

    public String getmReturn_date() {
        return mReturn_date;
    }

    public void setmReturn_date(String mReturn_date) {
        this.mReturn_date = mReturn_date;
    }

    public String ismNonstop() {
        return mNonstop;
    }

    public void setmNonstop(String mNonstop) {
        this.mNonstop = mNonstop;
    }

    public String getmMax_price() {
        return mMax_price;
    }

    public void setmMax_price(String mMax_price) {
        this.mMax_price = mMax_price;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(String mCurrency) {
        this.mCurrency = mCurrency;
    }

    public String getmMaxResults() {
        return mMaxResults;
    }

    public void setmMaxResults(String mMaxResults) {
        this.mMaxResults = mMaxResults;
    }

    public String getmNonstop() {
        return mNonstop;
    }

    public String getOneWay() {
        return oneWay;
    }

    public void setOneWay(String oneWay) {
        this.oneWay = oneWay;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    //endregion

}
