package com.example.android.flymetothemoon.FlightsDoc;

/**
 * Created by tsita on 14/1/2017.
 */

public class Flights {

    private String departs_at;
    private String departure_time;
    private String arrival_time;
    private String arrives_at;
    private String origin_airport;
    private String origin_airport_full_name;
    private String destination_airport;
    private String airline;
    private String duration;


    public String getAirline_full_name() {
        return airline_full_name;
    }

    public void setAirline_full_name(String airline_full_name) {
        this.airline_full_name = airline_full_name;
    }

    private String airline_full_name;
    private String flight_number;


    public Flights(String departs_at, String arrives_at, String origin_airport, String destination_airport, String airline, String flight_number) {
        this.departs_at = departs_at;
        this.arrives_at = arrives_at;
        this.origin_airport = origin_airport;
        this.destination_airport = destination_airport;
        this.airline = airline;
        this.flight_number = flight_number;
    }


    public String getDeparts_at() {
        return departs_at;
    }

    public void setDeparts_at(String departs_at) {
        this.departs_at = departs_at;
    }

    public String getArrives_at() {
        return arrives_at;
    }

    public void setArrives_at(String arrives_at) {
        this.arrives_at = arrives_at;
    }


    public String getOrigin_airport() {
        return origin_airport;
    }

    public void setOrigin_airport(String origin_airport) {
        this.origin_airport = origin_airport;
    }

    public String getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(String destination_airport) {
        this.destination_airport = destination_airport;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getOrigin_airport_full_name() {
        return origin_airport_full_name;
    }

    public void setOrigin_airport_full_name(String origin_airport_full_name) {
        this.origin_airport_full_name = origin_airport_full_name;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
