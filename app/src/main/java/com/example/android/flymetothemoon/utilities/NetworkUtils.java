package com.example.android.flymetothemoon.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.flymetothemoon.SearchActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import static com.example.android.flymetothemoon.BuildConfig.AMADEUS_API_KEY;
import static com.example.android.flymetothemoon.BuildConfig.IATA_API_KEY;

/**
 * Created by tsita on 13/1/2017.
 * These utilities will be used to communicate with the servers.
 */

public final class NetworkUtils {

    /*
    "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?
    apikey=AMADEUS_API_KEY.toString()
    &origin=SKG
    &destination=STR
    &departure_date=2017-01-25
    &return_date=2017-01-30
    &nonstop=true
    &max_price=400
    &currency=EUR"
     */


    private static final String TAG = NetworkUtils.class.getSimpleName(); //gia to Log.v()

    private static final String apikey = AMADEUS_API_KEY.toString();

    private static final String origin = "SKG";
    private static final String destination = "STR";
    private static final String departure_date = "2017-01-25";
    private static final String return_date = "2017-01-30";
    private static final boolean nonstop = true;
    private static final int max_price = 400;
    private static final String currency = "EUR";

    //Για το autocomplete
//    private static final String term = "ROM";

    // Για τις αεροπορικές
//    private static final String airlines_code = "A3";
    private static final String airlines_api_key = IATA_API_KEY.toString();


    //TODO(1) Φτιάξε και τα απαιτούμενα PARAMS

    final static String FLIGHTS_BASE_URL = "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search";
    final static String API_KEY_PARAM = "apikey";
    final static String ORIGIN_PARAM = "origin";
    final static String DESTINATION_PARAM = "destination";
    final static String DEPARTURE_DATE_PARAM = "departure_date";
    final static String RETURN_DATE_PARAM = "return_date";
    final static String NONSTOP_PARAM = "nonstop";
    final static String MAX_PRICE_PARAM = "max_price";
    final static String N_RESULTS_PARAM = "number_of_results";
    final static String CURRENCY_PARAM = "currency";

    //Για το autocomplete
    final static String AUTOCOMPLETE_BASE_URL = "https://api.sandbox.amadeus.com/v1.2/airports/autocomplete";
    final static String AUTOCOMPLETE_TERM_PARAM = "term";

    // Για τις αεροπορικές
    final static String AIRLINES_BASE_URL = "https://iatacodes.org/api/v6/airlines";
    final static String AIRLINES_API_KEY_PARAM = "api_key";
    final static String AIRLINES_CODE_PARAM = "code";

    //TODO(1) βρες ποια δεδομένα πρέπει να δίνεις ώστε να είναι σωστό το url από το API.




    /* για να φτιάξω το  uri με βάση αυτά που θέλει ο χρήστης θα πρέπει αν αλλάξω την υπογραφή της. θα παίρνει ως είσοδο αυτά που θέλω. */
    public static URL buildUrl(String origin,
                               String destination,
                               String departure_date,
                               String return_date,
                               String nonstop,
                               String max_price,
                               String number_of_results,
                               String currency){
        Uri builtUri = Uri.parse(FLIGHTS_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apikey)
                .appendQueryParameter(ORIGIN_PARAM, origin)
                .appendQueryParameter(DESTINATION_PARAM, destination)
                .appendQueryParameter(DEPARTURE_DATE_PARAM, departure_date)
                .appendQueryParameter(RETURN_DATE_PARAM, return_date)
                .appendQueryParameter(NONSTOP_PARAM, nonstop)
                .appendQueryParameter(MAX_PRICE_PARAM, max_price)
                .appendQueryParameter(N_RESULTS_PARAM, number_of_results)
                .appendQueryParameter(CURRENCY_PARAM, currency)
                .build();
        /* //TODO βάλε στην υπογραφή


    "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?
    apikey=IATA_API_KEY.toString()
    &origin=SKG
    &destination=STR
    &departure_date=2017-01-25
    &return_date=2017-01-30
    &nonstop=true
    &max_price=400
    &number_of_results=10
    &currency=EUR"
     */
        URL url = null;

        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);
        Log.v(TAG, "Check api key" +AMADEUS_API_KEY.toString());

        return url;
    }


    public static URL buildUrlForAutocomplete(String airport){
        //"https://api.sandbox.amadeus.com/v1.2/airports/autocomplete?
        // apikey=AMADEUS_API_KEY.toString()
        // &term=RMA"

        Uri builtUri = Uri.parse(AUTOCOMPLETE_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apikey)
                .appendQueryParameter(AUTOCOMPLETE_TERM_PARAM, airport)
                .build();
        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI for AUTOCOMPLETE " + url);
        return  url;
    }


    public static URL buildUrlForAirportTranslation(String airport){
        //"https://api.sandbox.amadeus.com/v1.2/airports/autocomplete?
        // apikey=AMADEUS_API_KEY.toString()
        // &term=RMA"

        Uri builtUri = Uri.parse(AUTOCOMPLETE_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apikey)
                .appendQueryParameter(AUTOCOMPLETE_TERM_PARAM, airport)
                .build();
        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI for AUTOCOMPLETE " + url);
        Log.v(TAG, "API key = " + apikey);
        return  url;
    }

    public static URL buildUrlForAirlines(String airline_code) {
        //https://iatacodes.org/api/v6/airlines?
        // api_key=IATA_API_KEY.toString()
        // &code=A3

        Uri builtUri = Uri.parse(AIRLINES_BASE_URL).buildUpon()
                .appendQueryParameter(AIRLINES_API_KEY_PARAM, airlines_api_key)
                .appendQueryParameter(AIRLINES_CODE_PARAM, airline_code)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI for AIRLINES " + url);
        Log.v(TAG, "Built URI for AIRLINES " + "ante reeeee");

        return  url;

    }


    public static  String getResponseFromHttpUrl(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}
