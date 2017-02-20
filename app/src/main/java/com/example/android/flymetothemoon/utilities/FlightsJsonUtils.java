package com.example.android.flymetothemoon.utilities;

import android.content.Context;
import android.util.Log;

import com.example.android.flymetothemoon.FlightsDoc.Airline;
import com.example.android.flymetothemoon.FlightsDoc.AirportsJsonTranslations;
import com.example.android.flymetothemoon.FlightsDoc.FlightResults;
import com.example.android.flymetothemoon.FlightsDoc.Flights;
import com.example.android.flymetothemoon.FlightsDoc.Itineraries;
import com.example.android.flymetothemoon.FlightsDoc.JsonResponseFlights;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.label;
import static android.R.attr.name;
import static android.R.attr.resource;
import static android.R.attr.value;

/**
 * Created by tsita on 13/1/2017.
 * Utility functions to handle JSON data.
 */

public final class FlightsJsonUtils {

    public static JsonResponseFlights getFlightsStringsFromJson(Context context, String flightsJsonStr) throws JSONException {


        // Όλες οι μεταβλητές που αρχίζουν με AM περιέχουν τα ακριβή ονόματα των τίτλων
        // στην απάντηση της υπηρεσίας στο json αρχείο.
        final String AM_CURRENCY = "currency";
        final String AM_RESULTS = "results";
        final String AM_ITINERARIES = "itineraries";
        final String AM_OUTBOUND = "outbound";
        final String AM_FLIGHTS = "flights";
        final String AM_DEPARTS_AT = "departs_at";
        final String AM_ARRIVES_AT = "arrives_at";
        final String AM_ORIGIN = "origin";
        final String AM_AIRPORT = "airport";
        final String AM_DESTINATION = "destination";
        final String AM_AIRLINE = "operating_airline";
        final String AM_FLIGHT_NUMBER = "flight_number";
        final String AM_INBOUND = "inbound";
        final String AM_FARE = "fare";
        final String AM_TOTAL_PRICE = "total_price";




        JSONObject flightsJson = new JSONObject(flightsJsonStr);

        String currency = flightsJson.getString(AM_CURRENCY);
        JSONArray flightsArray = flightsJson.getJSONArray(AM_RESULTS);

        // Αρχικοποίηση αρίθμισης αποτελεσμάτων. Δεν χρησιμεύει και πολύ. Είναι για διευκόλυνση.
        int numberOfFlights = 0 ;
        int numberOfItineraries = 0;
        int numberOfInboundFlights = 0;
        int numberOfOutboundFlights = 0;

        numberOfFlights = flightsArray.length();

        /* OUTBOUND */
        //TODO Οι μεταβλητές αυτές δεν χρησιμοποιούνται πλέον.  Πρέπει να διαγραφούν κάποια στιγμή.
        String[] outbound_departs_at = new String[flightsArray.length()];
        String[] outbound_arrives_at = new String[flightsArray.length()];
        String[] outbound_origin_airport = new String[flightsArray.length()];
        String[] outbound_destination_airport = new String[flightsArray.length()];
        String[] outbound_airline = new String[flightsArray.length()];
        String[] outbound_flight_number = new String[flightsArray.length()];

        /* INBOUND */
        //TODO Οι μεταβλητές αυτές δεν χρησιμοποιούνται πλέον.  Πρέπει να διαγραφούν κάποια στιγμή.
        String[] inbound_departs_at = new String[flightsArray.length()];
        String[] inbound_arrives_at = new String[flightsArray.length()];
        String[] inbound_origin_airport = new String[flightsArray.length()];
        String[] inbound_destination_airport = new String[flightsArray.length()];
        String[] inbound_airline = new String[flightsArray.length()];
        String[] inbound_flight_number = new String[flightsArray.length()];
        double[] price = new double[flightsArray.length()];

        // Αρχικοποίηση του αντικειμένου response και των ArrayList απο
        // τα οποία αποτελείται.
        JsonResponseFlights response;
        ArrayList<FlightResults> flightResults = new ArrayList<>();
      //  ArrayList<Itineraries> itineraries = new ArrayList<Itineraries>();
      //  ArrayList<Flights> outFlights = new ArrayList<Flights>();
      //  ArrayList<Flights> inFlights = new ArrayList<Flights>();

        // Αποκωδικοποίηση JSON
    /* κάθε 0,1,2 κλπ είναι ένα object και το παίρνω με getJSONObject
    *  όταν έχει brackets [] τότε είναι πίνακας και πρέπει να πάρω τον πίνακα με getJSONArray() που επιστρέφει JSONArray*/
        for (int i=0; i<flightsArray.length(); i++){
            JSONObject result = flightsArray.getJSONObject(i);

            JSONObject fareObject = result.getJSONObject(AM_FARE);
            price[i] = fareObject.getDouble(AM_TOTAL_PRICE);

            JSONArray itinerariesN = result.getJSONArray(AM_ITINERARIES);
            ArrayList<Itineraries> itineraries = new ArrayList<Itineraries>();
            numberOfItineraries = itinerariesN.length();

            for (int j=0; j<numberOfItineraries; j++){
                JSONObject itinerary = itinerariesN.getJSONObject(j);

                /* OUTBOUND information*/
                JSONObject outbound = itinerary.getJSONObject(AM_OUTBOUND);
                JSONArray outFlightsArr = outbound.getJSONArray(AM_FLIGHTS);
                ArrayList<Flights> outFlights = new ArrayList<Flights>();
                numberOfOutboundFlights = outFlightsArr.length();

                for (int k=0; k<numberOfOutboundFlights; k++){
                    JSONObject fl = outFlightsArr.getJSONObject(k);
                    outbound_departs_at[i] = fl.getString(AM_DEPARTS_AT);
                    outbound_arrives_at[i] = fl.getString(AM_ARRIVES_AT);

                    JSONObject origin = fl.getJSONObject(AM_ORIGIN);
                    outbound_origin_airport[i] = origin.getString(AM_AIRPORT);

                    JSONObject destination = fl.getJSONObject(AM_DESTINATION);
                    outbound_destination_airport[i] = destination.getString(AM_AIRPORT);

                    outbound_airline[i] = fl.getString(AM_AIRLINE);
                    outbound_flight_number[i] = fl.getString(AM_FLIGHT_NUMBER);

                    // Προσθήκη πτήσης στο ArrayList για outbound.
                    outFlights.add(new Flights(fl.getString(AM_DEPARTS_AT),
                            fl.getString(AM_ARRIVES_AT),
                            origin.getString(AM_AIRPORT),
                            destination.getString(AM_AIRPORT),
                            fl.getString(AM_AIRLINE),
                            fl.getString(AM_FLIGHT_NUMBER)));

                }

                /* INBOUND information*/
                JSONObject inbound = itinerary.getJSONObject(AM_INBOUND);
                JSONArray inFlightsArr = inbound.getJSONArray(AM_FLIGHTS);
                ArrayList<Flights> inFlights = new ArrayList<Flights>();
                numberOfInboundFlights = inFlightsArr.length();

                for (int k=0; k<numberOfInboundFlights; k++) {
                    JSONObject fl = inFlightsArr.getJSONObject(k);
                    inbound_departs_at[i] = fl.getString(AM_DEPARTS_AT);
                    inbound_arrives_at[i] = fl.getString(AM_ARRIVES_AT);

                    JSONObject origin = fl.getJSONObject(AM_ORIGIN);
                    inbound_origin_airport[i] = origin.getString(AM_AIRPORT);

                    JSONObject destination = fl.getJSONObject(AM_DESTINATION);
                    inbound_destination_airport [i] = destination.getString(AM_AIRPORT);

                    inbound_airline[i] = fl.getString(AM_AIRLINE);
                    inbound_flight_number[i] = fl.getString(AM_FLIGHT_NUMBER);

                    // Προσθήκη πτήσης στο ArrayList για inbound
                    inFlights.add(new Flights(fl.getString(AM_DEPARTS_AT),
                            fl.getString(AM_ARRIVES_AT),
                            origin.getString(AM_AIRPORT),
                            destination.getString(AM_AIRPORT),
                            fl.getString(AM_AIRLINE),
                            fl.getString(AM_FLIGHT_NUMBER)));
                }

                // Προσθήκη itinerary στο ArrayList με τα itineraries.
                itineraries.add(new Itineraries(outFlights, inFlights));


            }
            // Τελική προσθήκη ενός αποτελέσματος στο ArrayList με τα αποτελέσματα πτήσεων
            flightResults.add(new FlightResults(itineraries, fareObject.getString(AM_TOTAL_PRICE)));

        }

        // Δημιουργία του αντικειμένου που περιέχει όλη την απάντηση από το JSON
        // για τις πτήσεις που ψάχνουμε.
        response = new JsonResponseFlights(flightResults, currency, numberOfFlights, numberOfItineraries, numberOfInboundFlights, numberOfOutboundFlights);


        //Με αυτόν τον τρόπο παίρνω τα αποτελέσματα.
        response.getResults().get(0).getItineraries().get(0).getInbounds().get(0).getAirline();

        String msg = "\n\n\n\nΠληροφορίες για τις πτήσεις!\n\n Βρέθηκαν " + numberOfFlights + " πτήσεις!\n\n";
        for (int i=0; i<numberOfFlights; i++){
            msg += "Πτήση νούμερο: " + i + "\n";
            msg += "Από " + outbound_origin_airport[i] +"\n";
            msg += "Σε " + inbound_origin_airport[i] +"\n\n";
            msg += "Ημερομηνία αναχώρησης " + outbound_departs_at[i] + " με άφιξη στις " + outbound_arrives_at[i] +"\n";
            msg += "Εταιρία: " + outbound_airline[i] +"\n";
            msg += "Κωδικός πτήσης: " + outbound_flight_number[i] +"\n";

            msg += "Ημερομηνία επιστροφής " + inbound_departs_at[i] + " με άφιξη στις " + inbound_arrives_at[i] +"\n";
            msg += "Εταιρία: " + inbound_airline[i] +"\n";
            msg += "Κωδικός πτήσης: " + inbound_flight_number[i] +"\n";

            msg += "Συνολικό κόστος: " + price[i] + " σε νόμισμα: " + currency +"\n";
            msg += "***********************************\n***********************************\n***********************************\n***********************************\n";
        }


        Log.v("FlightsJsonUtils", msg );

        return response;
    }

    public static String[] autocompletAirport(String cityJsonStr) throws  JSONException {
        //"https://api.sandbox.amadeus.com/v1.2/airports/autocomplete?
        // apikey=AMADEUS_API_KEY.toString()
        // &term=RMA"

        final String AM_AUTOCOMPLETE_VALUE = "value";
        final String AM_AUTOCOMPLETE_LABEL = "label";

        JSONArray autocompleteResults = new JSONArray(cityJsonStr);

        int numberOfResultsAutocomplete = autocompleteResults.length();

        String[] value = new String[numberOfResultsAutocomplete];
        String[] label = new String[numberOfResultsAutocomplete];


        for (int i=0; i<numberOfResultsAutocomplete; i++) {
            JSONObject result = autocompleteResults.getJSONObject(i);

            value[i] = result.getString(AM_AUTOCOMPLETE_VALUE);
            label[i] = result.getString(AM_AUTOCOMPLETE_LABEL);
        }


        String msg = "" + numberOfResultsAutocomplete + " στοιχεία που ανακτήθηκαν από την τιμή " + cityJsonStr + " και είναι τα εξής:\n";
        for (int i=0; i< numberOfResultsAutocomplete; i++){
            msg += "[" + i + "] Value = " + value[i] + " \t";
            msg += "Label = " + label[i] + "\n\n";
        }


        Log.v("FlightsJsonUtils", msg);
        return label;
    }

    public static ArrayList<Airline> airlinesJson(String codeJsonStr) throws  JSONException {
        //https://iatacodes.org/api/v6/airlines?
        // api_key=2d795f6e-a61c-4697-aff4-50df2d00dfb0
        // &code=A3

        final String IA_AIRLINES_RESPONSE = "response";
        final String IA_AIRLINES_CODE = "code";
        final String IA_AIRLINES_NAME = "name";

        JSONObject airlinesResults = new JSONObject(codeJsonStr);

        ArrayList<Airline> airlines = new ArrayList<Airline>();

        JSONArray responses = airlinesResults.getJSONArray(IA_AIRLINES_RESPONSE);
        for (int i=0; i<responses.length(); i++){
            airlines.add(new Airline(responses.getJSONObject(i).getString(IA_AIRLINES_NAME), responses.getJSONObject(i).getString(IA_AIRLINES_CODE)));
        }

        String msg = "" + airlinesResults.length() + " Αποτελέσματα βρέθηκαν.\n";
        for (int i=0; i<airlines.size(); i++) {
            msg += "[" + i + "] Η εταιρία με κωδικό: " + airlines.get(i).getCode() + " είναι η αεροπορική εταιρία: " + airlines.get(i).getName() +"\n";
            msg += "πόσο ακόμα ρεεεε?" ;
        }

        Log.v("FlightsJsonUtils", msg);
        return airlines;

    }

    public static ArrayList<AirportsJsonTranslations> airportTranslationJson(String termJsonStr) throws JSONException {
        //https://api.sandbox.amadeus.com/v1.2/airports/autocomplete?
        // apikey=AMADEUS_API_KEY.toString() PREPEI NA ALLAKSEI
        // &term=RMA

        final String AM_AIRPORT_VALUE = "value";
        final String AM_AIRPORT_LABEL = "label";

        JSONArray airport = new JSONArray(termJsonStr);
        ArrayList<AirportsJsonTranslations> airportsJsonTranslations = new ArrayList<AirportsJsonTranslations>();

        for (int i=0; i<airport.length(); i++){
            airportsJsonTranslations.add(new AirportsJsonTranslations(airport.getJSONObject(i).getString(AM_AIRPORT_VALUE), airport.getJSONObject(i).getString(AM_AIRPORT_LABEL)));
        }

        return airportsJsonTranslations;
        /*
            [
                {
                    "value": "RMA",
                    "label": "Roma [RMA]"
                }
            ]
         */
    }


}
