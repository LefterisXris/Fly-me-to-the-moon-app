package com.example.android.flymetothemoon.FlightsDoc;

import java.util.ArrayList;

/**
 * Created by tsita on 14/1/2017.
 */

public class JsonResponseFlights {
    private ArrayList<FlightResults> results;
    private String currency;
    private int numberOfFlights;
    private int numberOfItineraries;
    private int numberOfInboundFlights;
    private int numberOfOutboundFlights;


    public JsonResponseFlights(ArrayList<FlightResults> results, String currency,
                               int numberOfFlights, int numberOfItineraries, int numberOfInboundFlights, int numberOfOutboundFlights) {
        this.results = results;
        this.currency = currency;
        this.numberOfFlights = numberOfFlights;
        this.numberOfItineraries = numberOfItineraries;
        this.numberOfInboundFlights = numberOfInboundFlights;
        this.numberOfOutboundFlights = numberOfOutboundFlights;
    }

    public ArrayList<FlightResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<FlightResults> results) {
        this.results = results;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getNumberOfFlights() {
        return numberOfFlights;
    }

    public void setNumberOfFlights(int numberOfFlights) {
        this.numberOfFlights = numberOfFlights;
    }

    public int getNumberOfItineraries() {
        return numberOfItineraries;
    }

    public void setNumberOfItineraries(int numberOfItineraries) {
        this.numberOfItineraries = numberOfItineraries;
    }

    public int getNumberOfInboundFlights() {
        return numberOfInboundFlights;
    }

    public void setNumberOfInboundFlights(int numberOfInboundFlights) {
        this.numberOfInboundFlights = numberOfInboundFlights;
    }

    public int getNumberOfOutboundFlights() {
        return numberOfOutboundFlights;
    }

    public void setNumberOfOutboundFlights(int numberOfOutboundFlights) {
        this.numberOfOutboundFlights = numberOfOutboundFlights;
    }

}
