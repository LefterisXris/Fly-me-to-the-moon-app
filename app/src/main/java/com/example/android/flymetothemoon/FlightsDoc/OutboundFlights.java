package com.example.android.flymetothemoon.FlightsDoc;

/**
 * Created by tsita on 14/1/2017.
 */
//TODO delete this class. Δεν χρησιμοποιείται πουθενά.
public class OutboundFlights extends Flights {

    private boolean outbound;


    public OutboundFlights(boolean isOutbound, String departs_at, String arrives_at, String origin_airport, String destination_airport, String airline, String flight_number) {
        super(departs_at, arrives_at, origin_airport, destination_airport, airline, flight_number);
        outbound = isOutbound;

    }

    @Override
    public String getDeparts_at() {
        return super.getDeparts_at();
    }

    @Override
    public void setDeparts_at(String departs_at) {
        super.setDeparts_at(departs_at);
    }

    @Override
    public String getArrives_at() {
        return super.getArrives_at();
    }

    @Override
    public void setArrives_at(String arrives_at) {
        super.setArrives_at(arrives_at);
    }

    @Override
    public String getOrigin_airport() {
        return super.getOrigin_airport();
    }

    @Override
    public void setOrigin_airport(String origin_airport) {
        super.setOrigin_airport(origin_airport);
    }

    @Override
    public String getDestination_airport() {
        return super.getDestination_airport();
    }

    @Override
    public void setDestination_airport(String destination_airport) {
        super.setDestination_airport(destination_airport);
    }

    @Override
    public String getAirline() {
        return super.getAirline();
    }

    @Override
    public void setAirline(String airline) {
        super.setAirline(airline);
    }

    @Override
    public String getFlight_number() {
        return super.getFlight_number();
    }

    @Override
    public void setFlight_number(String flight_number) {
        super.setFlight_number(flight_number);
    }

    public boolean isOutbound() {
        return outbound;
    }

    public void setOutbound(boolean outbound) {
        this.outbound = outbound;
    }
}
