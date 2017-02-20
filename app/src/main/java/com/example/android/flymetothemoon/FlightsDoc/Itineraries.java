package com.example.android.flymetothemoon.FlightsDoc;

import java.util.ArrayList;

/**
 * Created by tsita on 14/1/2017.
 */

public class Itineraries{
    private ArrayList<Flights> outbound;
    private ArrayList<Flights> inbounds;


    public Itineraries(ArrayList<Flights> outbound, ArrayList<Flights> inbounds) {
        this.outbound = outbound;
        this.inbounds = inbounds;
    }

    public ArrayList<Flights> getInbounds() {
        return inbounds;
    }

    public void setInbounds(ArrayList<Flights> inbounds) {
        this.inbounds = inbounds;
    }

    public ArrayList<Flights> getOutbound() {
        return outbound;
    }

    public void setOutbound(ArrayList<Flights> outbound) {
        this.outbound = outbound;
    }

}
