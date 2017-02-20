package com.example.android.flymetothemoon.FlightsDoc;

import java.util.ArrayList;

/**
 * Created by tsita on 14/1/2017.
 */

public class FlightResults {
    private ArrayList<Itineraries> itineraries;
    private String price;

    public FlightResults(ArrayList<Itineraries> itineraries, String price) {
        this.itineraries = itineraries;
        this.price = price;
    }

    public ArrayList<Itineraries> getItineraries() {
        return itineraries;
    }

    public void setItineraries(ArrayList<Itineraries> itineraries) {
        this.itineraries = itineraries;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
