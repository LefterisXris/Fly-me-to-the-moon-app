package com.example.android.flymetothemoon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.flymetothemoon.FlightsDoc.Airline;
import com.example.android.flymetothemoon.FlightsDoc.AirportsJsonTranslations;
import com.example.android.flymetothemoon.FlightsDoc.JsonResponseFlights;
import com.example.android.flymetothemoon.utilities.FlightsJsonUtils;
import com.example.android.flymetothemoon.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.android.flymetothemoon.R.id.text1;
import static com.example.android.flymetothemoon.R.id.tvHeaderProgress;
import static com.example.android.flymetothemoon.R.xml.prefs;

public class ShowResultsActivity extends AppCompatActivity {


    private static final String TAG = ShowResultsActivity.class.getSimpleName(); //gia to Log.v()


    private TextView mSearchResultsTextView;
    private TextView mErrorMessageTextView;
    private TextView mHeadingTextView;
    private ProgressBar mIndicatorProgressBar;

    //times apo to intent
    private String origin;
    private String destination;
    private String departure_date;
    private String return_date;
    private String is_one_way;
    private String is_non_stop;
    private String max_price;
    private String persons;
    private String maxResults = "10";
    private String currency = "EUR";

    private  LinearLayout linlaHeaderProgress;
    private TextView mTvHeaderProgress;

    // gia details
    private TextView mDetailsOutboundOriginAirportTextView;
    private TextView mDetailsOutboundOriginAirportShortTextView;
    private TextView mDetailsOutboundDestinationAirportTextView;
    private TextView mDetailsOutboundDestinationAirportShortTextView;
    private TextView mDetailsOutboundDepartureDateTextView;

    private TextView mDetailsInboundOriginAirportTextView;
    private TextView mDetailsInboundOriginAirportShortTextView;
    private TextView mDetailsInboundDestinationAirportTextView;
    private TextView mDetailsInboundDestinationAirportShortTextView;
    private TextView mDetailsInboundDepartureDateTextView;

    private FlightQuery flightQuery;
    private ResultAdapter resultAdapter;
    private ListView listView;
    private JsonResponseFlights response;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            origin = extras.getString("origin");
            destination = extras.getString("destination");
            departure_date = extras.getString("departure_date");
            return_date = extras.getString("return_date");
            is_one_way = extras.getString("is_one_way");
            is_non_stop = extras.getString("is_non_stop");
            max_price = extras.getString("max_price");
            persons = extras.getString("persons");


        }
        setContentView(R.layout.activity_show_results);

        /* Ορισμός μεταβλητών από τις ρυθμίσεις */
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currency = sharedPreferences.getString(
                getString(R.string.pref_currency_key),
                getString(R.string.pref_currency_default_value));

        maxResults = sharedPreferences.getString(
                getString(R.string.pref_number_of_results_key),
                getString(R.string.pref_number_of_results_default_value));

        max_price = sharedPreferences.getString(
                getString(R.string.pref_max_price_key),
                getString(R.string.pref_max_price_default_value));




        // HEADER
        mDetailsOutboundOriginAirportTextView = (TextView) findViewById(R.id.details_outbound_origin_airport_text_view);
        mDetailsOutboundOriginAirportShortTextView = (TextView) findViewById(R.id.details_outbound_origin_airport_short_text_view);
        mDetailsOutboundDestinationAirportTextView = (TextView) findViewById(R.id.details_outbound_destination_airport_text_view);
        mDetailsOutboundDestinationAirportShortTextView = (TextView) findViewById(R.id.details_outbound_destination_airport_short_text_view);
        mDetailsOutboundDepartureDateTextView = (TextView) findViewById(R.id.details_outbound_departure_date_text_view);

        mDetailsInboundOriginAirportTextView = (TextView) findViewById(R.id.details_inbound_departure_origin_airport_text_view);
        mDetailsInboundOriginAirportShortTextView = (TextView) findViewById(R.id.details_inbound_departure_origin_airport_short_text_view);
        mDetailsInboundDestinationAirportTextView = (TextView) findViewById(R.id.details_inbound_arrival_airport_text_view);
        mDetailsInboundDestinationAirportShortTextView = (TextView) findViewById(R.id.details_inbound_arrival_airport_short_text_view);
        mDetailsInboundDepartureDateTextView = (TextView) findViewById(R.id.details_inbound_arrival_date_text_view);


        // Ανάθεση τιμών βάση χρήστη.
        mDetailsOutboundOriginAirportTextView.setText(origin);
       // mDetailsOutboundOriginAirportShortTextView.setText("(" + origin + ")");
        mDetailsOutboundDestinationAirportTextView.setText(destination);
     //   mDetailsOutboundDestinationAirportShortTextView.setText("(" + destination + ")");
        mDetailsOutboundDepartureDateTextView.setText(departure_date);

        mDetailsInboundOriginAirportTextView.setText(destination);
     //   mDetailsInboundOriginAirportShortTextView.setText("(" + destination + ")");
        mDetailsInboundDestinationAirportTextView.setText(origin);
      //  mDetailsInboundDestinationAirportShortTextView.setText("(" + origin + ")");
        mDetailsInboundDepartureDateTextView.setText(return_date);


/*
        mSearchResultsTextView = (TextView) findViewById(R.id.results_text_view);
        mErrorMessageTextView = (TextView) findViewById(R.id.tv_error_message_display);
        mHeadingTextView = (TextView) findViewById(R.id.heading_text_view);
        mIndicatorProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
*/

       // ArrayList<FlightQuery> flightQueries = new ArrayList<FlightQuery>();
        flightQuery = new FlightQuery(origin, destination, departure_date, return_date, is_non_stop, max_price, maxResults ,currency);

        //flightQueries.add(new FlightQuery(origin, destination, departure_date, return_date, is_non_stop, max_price, maxResults ,currency));
        //flightQueries.add(new FlightQuery("SKG", "STR", "2017-01-25", "2017-01-30", "true", "400", "EUR"));
        //Log.v(TAG, ""+flightQueries.get(0));

        linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        mTvHeaderProgress = (TextView) findViewById(tvHeaderProgress);

       // new FetchFlightsTask().execute(flightQueries.get(0));
        new FetchFlightsTask().execute(flightQuery);

    }



    public void showJsonDataView(){
   /*     mSearchResultsTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
*/
//// TODO: 5/2/2017 remove it
    }

    public void refreshResults(){

        flightQuery = new FlightQuery(origin, destination, departure_date, return_date, is_non_stop, max_price, maxResults ,currency);

        new FetchFlightsTask().execute(flightQuery);
    }

    public void setOnclickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowResultsActivity.this, DetailActivity.class);


                String name;
                String outbound_flight_number;
                String go_dep_time;
                String go_arr_time;
                String go_dur;
                String inbound_flight_number;
                String ret_dep_time;
                String ret_arr_time;
                String ret_dur;
                String price;

                String origin_airport;
                String return_airport;
                String origin_date;
                String return_date;


                name = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getAirline_full_name();
                outbound_flight_number = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getFlight_number();
                go_dep_time = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getDeparture_time();
                go_arr_time = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getArrival_time();
                go_dur = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getDuration();
                inbound_flight_number = response.getResults().get(position).getItineraries().get(0).getInbounds().get(0).getFlight_number();

                ret_dep_time = response.getResults().get(position).getItineraries().get(0).getInbounds().get(0).getDeparture_time();
                ret_arr_time = response.getResults().get(position).getItineraries().get(0).getInbounds().get(0).getArrival_time();
                ret_dur = response.getResults().get(position).getItineraries().get(0).getInbounds().get(0).getDuration();
                price = response.getResults().get(position).getPrice();

//                if (currency.equals("EUR")) {
//                    price += " €";
//                }
//                else{
//                    price += "$";
//                }

                origin_airport = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getOrigin_airport_full_name();
                return_airport = response.getResults().get(position).getItineraries().get(0).getInbounds().get(0).getOrigin_airport_full_name();

                origin_date = response.getResults().get(position).getItineraries().get(0).getOutbound().get(0).getDeparts_at();
                return_date = response.getResults().get(position).getItineraries().get(0).getInbounds().get(0).getDeparts_at();

                intent.putExtra("name", name);
                intent.putExtra("outbound_flight_number", outbound_flight_number);
                intent.putExtra("go_dep_time", go_dep_time);
                intent.putExtra("go_arr_time", go_arr_time);
                intent.putExtra("go_dur", go_dur);
                intent.putExtra("inbound_flight_number", inbound_flight_number);
                intent.putExtra("ret_dep_time", ret_dep_time);
                intent.putExtra("ret_arr_time", ret_arr_time);
                intent.putExtra("ret_dur", ret_dur);
                intent.putExtra("price", price);

                intent.putExtra("origin_airport", origin_airport);
                intent.putExtra("return_airport", return_airport);
                intent.putExtra("origin_date", origin_date);
                intent.putExtra("return_date", return_date);

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.results_options, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mCurrency = sharedPreferences.getString(
                getString(R.string.pref_currency_key),
                getString(R.string.pref_currency_default_value));

        String mMaxResults = sharedPreferences.getString(
                getString(R.string.pref_number_of_results_key),
                getString(R.string.pref_number_of_results_default_value));

        String mMax_price = sharedPreferences.getString(
                getString(R.string.pref_max_price_key),
                getString(R.string.pref_max_price_default_value));

        boolean hasChanged = false;

        if (!currency.equals(mCurrency) || !maxResults.equals(mMaxResults) || !max_price.equals(mMax_price)) {
            hasChanged = true;
        }

        currency = mCurrency;
        maxResults = mMaxResults;
        max_price = mMax_price;


        //TODO remove it
        String msg = "";
        msg += "Currency = " + currency + "\n";
        msg += "Max yolo Results = " + maxResults + "\n";
        msg += "Max Price = "+ max_price + "\n";
        Log.v(TAG, msg);

        if (hasChanged) {
            refreshResults();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                //refresh;
                refreshResults();
                break;
            case R.id.settings_option:
                //open Setting
                Intent intent = new Intent(this, SettingsActivity.class);
               /* Toast toast = Toast.makeText(this, "Μη διαθέσιμο..." ,Toast.LENGTH_LONG);
                toast.show();*/
                startActivity(intent);
                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void showErrorMessage() {
        mTvHeaderProgress.setVisibility(View.GONE);
        ProgressBar pb = (ProgressBar) findViewById(R.id.pbHeaderProgress);
        pb.setVisibility(View.GONE);

        TextView error_msg = (TextView) findViewById(R.id.error_01);
        TextView error_msg2 = (TextView) findViewById(R.id.error_02);
        error_msg.setVisibility(View.VISIBLE);
        error_msg2.setVisibility(View.VISIBLE);

        /*Intent intent = new Intent(this, SettingsActivity.class);
                Toast toast = Toast.makeText(this, "Μη διαθέσιμο..." ,Toast.LENGTH_LONG);
                toast.show();
        startActivity(intent);*/
    }

    public String getTime(String date){
        String time = date.substring(date.indexOf("T")+1);
        return time;
    }

    public String subTime(String start, String end){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = dateFormat.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            date2 = dateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = date2.getTime() - date1.getTime();

        long timeInSeconds = diff / 1000;
        long hours, minutes, seconds;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        timeInSeconds = timeInSeconds - (minutes * 60);
        seconds = timeInSeconds;

        String diffTime = (hours<10 ? "0" + hours : hours) + " ώρ : " + (minutes < 10 ? "0" + minutes : minutes + " λ");


        return diffTime;
    }

    public class FetchFlightsTask extends AsyncTask<FlightQuery, Void, JsonResponseFlights> {

        @Override
        protected JsonResponseFlights doInBackground(FlightQuery... params) {

            if (params.length == 0){
                return null;
            }

             /* URL για μετατροπή του αεροδρομίου σε κατανοητή μορφή */
            String[] originsCutted = origin.split(" ");

            URL originAirportTranslationUrl = NetworkUtils.buildUrlForAirportTranslation(originsCutted[0]);

            String[] destinatiosCutted = destination.split(" ");
            URL returnAirportTranslationUrl = NetworkUtils.buildUrlForAirportTranslation(destinatiosCutted[0]);

            String jsonOriginAirportTranslationResponse = null;
            String jsonReturnAirportTranslationResponse = null;
            ArrayList<AirportsJsonTranslations> OriginAirport = null;
            ArrayList<AirportsJsonTranslations> ReturnAirport = null;

            try { //εκτέλεση URL και επιστροφή json
                jsonOriginAirportTranslationResponse = NetworkUtils
                        .getResponseFromHttpUrl(originAirportTranslationUrl);

                //αποκωδικοποίηση json και εισαγωγή ονόματος αεροδρομίου.
                OriginAirport = FlightsJsonUtils.airportTranslationJson(jsonOriginAirportTranslationResponse);


                jsonReturnAirportTranslationResponse = NetworkUtils
                        .getResponseFromHttpUrl(returnAirportTranslationUrl);

                //αποκωδικοποίηση json και εισαγωγή ονόματος αεροδρομίου.
                ReturnAirport = FlightsJsonUtils.airportTranslationJson(jsonReturnAirportTranslationResponse);


            } catch (IOException | JSONException e) {
                e.printStackTrace();
                cancel(true);
            }


            response = null;

            /* URL για εύρεση πτήσεων */
            if (!isCancelled()) {
                URL flightsRequestUrl = NetworkUtils.buildUrl(OriginAirport.get(0).getValue(), ReturnAirport.get(0).getValue(), params[0].getmDeparrture_date(),
                        params[0].getmReturn_date(), params[0].ismNonstop(), params[0].getmMax_price(), params[0].getmMaxResults(), params[0].getmCurrency());

                String jsonFlightResponse = null;

                try {
                    if (!isCancelled()) {
                        jsonFlightResponse = NetworkUtils
                                .getResponseFromHttpUrl(flightsRequestUrl);
                        response = FlightsJsonUtils.getFlightsStringsFromJson(ShowResultsActivity.this, jsonFlightResponse);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    cancel(true);
                }
            }
 /*  EISAGOGI TIMON */
            if (!isCancelled()) {
                for (int i = 0; i < OriginAirport.size(); i++) {
                    if (OriginAirport.get(i).getValue().equals(response.getResults().get(0).getItineraries().get(0).getOutbound().get(0).getOrigin_airport())) {
                        for (int j = 0; j < response.getNumberOfFlights(); j++) {
                            response.getResults().get(j).getItineraries().get(0).getOutbound().get(0).setOrigin_airport_full_name(OriginAirport.get(i).getLabel());
                        }

                    }
                }

                for (int i = 0; i < ReturnAirport.size(); i++) {
                    if (ReturnAirport.get(i).getValue().equals(response.getResults().get(0).getItineraries().get(0).getInbounds().get(0).getOrigin_airport())) {
                        for (int j = 0; j < response.getNumberOfFlights(); j++) {
                            response.getResults().get(j).getItineraries().get(0).getInbounds().get(0).setOrigin_airport_full_name(ReturnAirport.get(i).getLabel());
                        }
                    }
                }


            /* URL για αντιστοίχηση αεριοπορικών εταιρίών */
                for (int j = 0; j < response.getNumberOfFlights(); j++) {
                    URL airlinesRequestUrl = NetworkUtils.buildUrlForAirlines(response.getResults().get(j).getItineraries()
                            .get(0).getOutbound().get(0).getAirline());

                    String jsonAirlinesResponse = null;
                    try {
                        if (!isCancelled()) {
                            jsonAirlinesResponse = NetworkUtils
                                    .getResponseFromHttpUrl(airlinesRequestUrl);

                            ArrayList<Airline> airline = FlightsJsonUtils.airlinesJson(jsonAirlinesResponse);

                            for (int i = 0; i < airline.size(); i++) {
                                if (airline.get(i).getCode().equals(response.getResults().get(j).getItineraries().get(0).getOutbound().get(0).getAirline())) {
                                    response.getResults().get(j).getItineraries().get(0).getOutbound().get(0).setAirline_full_name(airline.get(i).getName());
                                    response.getResults().get(j).getItineraries().get(0).getInbounds().get(0).setAirline_full_name(airline.get(i).getName());
                                }
                            }


                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                        cancel(true);
                    }
                }
            }

            for (int i=0;i<response.getNumberOfFlights();i++) {
                String price = response.getResults().get(i).getPrice();

                if (currency.equals("EUR")) {
                    price += " €";
                } else {
                    price += " $";
                }
                response.getResults().get(i).setPrice(price);
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
         //   mIndicatorProgressBar.setVisibility(View.VISIBLE);
            linlaHeaderProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onCancelled() {
            showErrorMessage();
        }

        @Override
        protected void onPostExecute(JsonResponseFlights response) {

//            Log.v("ShowResultsActivity", "flights = " + flights[0]);
//            Log.v("ShowResultsActivity", "flights = " + flights[1]);

          //  mIndicatorProgressBar.setVisibility(View.INVISIBLE);

            //response.getResults().get(0).getItineraries().get(0).getInbounds().get(0).getAirline();

            if (response.getResults().size() > 0 && response != null){
//                String msg = "";
//                msg += "Βρέθηκαν " + response.getResults().size() + " αποτελέσματα!\n" ;
//                for (int i=0; i<response.getNumberOfFlights(); i++)
//                {
//                    for (int j=0; j<response.getNumberOfItineraries(); j++){
//                        for (int k=0; k<response.getNumberOfOutboundFlights(); k++){
//                            msg += "Πτήση από " + response.getResults().get(i).getItineraries().get(j).getOutbound().get(k).getOrigin_airport()
//                                    + " \nγια " + response.getResults().get(i).getItineraries().get(j).getOutbound().get(k).getDestination_airport()
//                                    + "\nστις " + response.getResults().get(i).getItineraries().get(j).getOutbound().get(k).getDeparts_at()
//                                    + "\nκαι άφιξη στις " + response.getResults().get(i).getItineraries().get(j).getOutbound().get(k).getArrives_at()
//                                    + "\n με την εταιρία " + response.getResults().get(i).getItineraries().get(j).getOutbound().get(k).getAirline()
//                                    + " κωδικός πτήσης " + response.getResults().get(i).getItineraries().get(j).getOutbound().get(k).getFlight_number() + "\n";
//                        }
//                        for (int k=0; k<response.getNumberOfInboundFlights(); k++){
//                            msg += "Επιστροφή από " + response.getResults().get(i).getItineraries().get(j).getInbounds().get(k).getOrigin_airport()
//                                    + "\nγια " + response.getResults().get(i).getItineraries().get(j).getInbounds().get(k).getDestination_airport()
//                                    + "\nστις " + response.getResults().get(i).getItineraries().get(j).getInbounds().get(k).getDeparts_at()
//                                    + "\nκαι άφιξη στις " + response.getResults().get(i).getItineraries().get(j).getInbounds().get(k).getArrives_at()
//                                    + "\n με την εταιρία " + response.getResults().get(i).getItineraries().get(j).getInbounds().get(k).getAirline()
//                                    + " κωδικός πτήσης " + response.getResults().get(i).getItineraries().get(j).getInbounds().get(k).getFlight_number() + "\n";
//                        }
//
//
//                    }
//
//                    msg += "Συνολική τιμή: " + response.getResults().get(i).getPrice()
//                            + " σε νόμισμα " + response.getCurrency() + "\n" ;
//
//                }
//                msg += "Αριθμός itineraries: "  + response.getNumberOfItineraries()
//                        + "\nΑριθμός πτήσεων out: " + response.getNumberOfOutboundFlights()
//                        + "\nΑριθμός πτήσεων in: " + response.getNumberOfInboundFlights() + "\n";

         //       mSearchResultsTextView.setText(msg);
                //TODO Βάλε τα δεδομένα όμορφα στην οθόνη με τον adapter.
                //TODO Πρόσθεσε μενού επιλογών για γλώσσα, νόμισμα και σκέψου κι άλλα.

                //TODO κάνε καινούργια συνάρτηση ώστε να είναι ξεχωριστά η εμφάνιση των αποτελεσμάτων η οποία θα καλείται από την post execute.

                /* ΜΟΡΦΟΠΟΙΗΣΗ ΩΡΑΣ και ΔΙΑΡΚΕΙΑΣ*/
                for (int i=0;i<response.getNumberOfFlights();i++){

                    String goDepartureTime = getTime(response.getResults().get(i).getItineraries().get(0).getOutbound().get(0).getDeparts_at());
                    String goArrivalTime = getTime(response.getResults().get(i).getItineraries().get(0).getOutbound().get(0).getArrives_at());
                    String returnDepartureTime = getTime(response.getResults().get(i).getItineraries().get(0).getInbounds().get(0).getDeparts_at());
                    String returnArrivalTime = getTime(response.getResults().get(i).getItineraries().get(0).getInbounds().get(0).getArrives_at());

                    response.getResults().get(i).getItineraries().get(0).getOutbound().get(0).setDeparture_time(goDepartureTime);
                    response.getResults().get(i).getItineraries().get(0).getOutbound().get(0).setArrival_time(goArrivalTime);

                    response.getResults().get(i).getItineraries().get(0).getInbounds().get(0).setDeparture_time(returnDepartureTime);
                    response.getResults().get(i).getItineraries().get(0).getInbounds().get(0).setArrival_time(returnArrivalTime);

                    /* ΜΟΡΦΟΠΟΙΗΣΗ διάρκειας */
                    String outboundDuration = subTime(goDepartureTime, goArrivalTime);
                    String inboundDuration = subTime(returnDepartureTime, returnArrivalTime);

                    response.getResults().get(i).getItineraries().get(0).getOutbound().get(0).setDuration(outboundDuration);
                    response.getResults().get(i).getItineraries().get(0).getInbounds().get(0).setDuration(inboundDuration);
                }

                /* ΜΟΡΦΟΠΟΙΗΣΗ ΑΕΡΟΔΡΟΜΙΟΥ */
                mDetailsOutboundOriginAirportTextView.setText(response.getResults().get(0).getItineraries().get(0).getOutbound().get(0).getOrigin_airport_full_name());
                mDetailsOutboundDestinationAirportTextView.setText(response.getResults().get(0).getItineraries().get(0).getInbounds().get(0).getOrigin_airport_full_name());

                mDetailsInboundOriginAirportTextView.setText(response.getResults().get(0).getItineraries().get(0).getInbounds().get(0).getOrigin_airport_full_name());
                mDetailsInboundDestinationAirportTextView.setText(response.getResults().get(0).getItineraries().get(0).getOutbound().get(0).getOrigin_airport_full_name());



                /* ΜΟΡΦΟΠΟΙΗΣΗ ΤΙΜΗΣ */ //TODO




                resultAdapter = new ResultAdapter(ShowResultsActivity.this, response.getResults());
                listView = (ListView) findViewById(R.id.activity_show_results);

                listView.setAdapter(resultAdapter);



                linlaHeaderProgress.setVisibility(View.GONE);
                mTvHeaderProgress.setVisibility(View.GONE);



                setOnclickItem();
                showJsonDataView();
            }
            else{
                showErrorMessage();
            }



//            if (flights.length > 0) {
//                mSearchResultsTextView.setText(flights[0]);
//                mHeadingTextView.setText("Επιτυχής ανάκτηση δεδομένων!");
//                showJsonDataView();
//                try {
//                    FlightsJsonUtils.getFlightsStringsFromJson(ShowResultsActivity.this, flights[0]);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    FlightsJsonUtils.autocompletAirport(ShowResultsActivity.this, flights[1]);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    FlightsJsonUtils.airlinesJson(ShowResultsActivity.this, flights[2]);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//                showErrorMessage();
//            }



//            if (flights != null){
//                for (String flightString : flights){
//                    mResultsTextView.append((flightString) + "\n\n\n");
//                }
//            }

        }

    }
}
