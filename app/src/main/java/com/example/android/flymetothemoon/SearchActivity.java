package com.example.android.flymetothemoon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.flymetothemoon.FlightsDoc.JsonResponseFlights;
import com.example.android.flymetothemoon.utilities.FlightsJsonUtils;
import com.example.android.flymetothemoon.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;


import static com.example.android.flymetothemoon.R.array.available_airports;

public class SearchActivity extends AppCompatActivity {

    private Button arrival_date_button;
    private TextView display_arrival_date;

    private Button departure_date_button;
    private TextView display_departure_date;


    private TextView mDepartureDateTextView;
    private TextView mArrivalDateTextView;
    private CheckBox mOneWayCheckbox;
    private CheckBox mDirectFlightCheckbox;
    private AutoCompleteTextView autocompleteOriginAirport;
    private AutoCompleteTextView autocompleteDestinationAirport;


    private String departure_date_formated;
    private String return_date_formated;


    private int year;
    private int month;
    private int day;

    private String year_formatted;
    private String month_formatted;
    private String day_formatted;

    private int dateHasChange = -1;


    static final int DATE_DEPARTURE_DIALOG_ID = 998;
    static final int DATE_ARRIVAL_DIALOG_ID = 999;

    private boolean departureHasSetted = false;
    private boolean arrivalHasSetted = false;

    private String max_price;
    private String maxResults = "10";
    private String currency = "EUR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mDepartureDateTextView = (TextView) findViewById(R.id.departure_date_text_view);
        mArrivalDateTextView = (TextView) findViewById(R.id.arrival_date_text_view);
        mOneWayCheckbox = (CheckBox) findViewById(R.id.one_way_checkbox);
        mDirectFlightCheckbox = (CheckBox) findViewById(R.id.direct_flight_checkbox);
        departure_date_button = (Button) findViewById(R.id.button_departure_date);
        arrival_date_button = (Button) findViewById(R.id.button_arrival_date);

        autocompleteOriginAirport = (AutoCompleteTextView) findViewById(R.id.autocompleteOriginAirport);
        autocompleteDestinationAirport = (AutoCompleteTextView) findViewById(R.id.autocompleteDestinationAirport);


        autocompleteOriginAirport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autocompleteOriginAirport.setSelection(0); // cursor to start
                hideKeyboard(autocompleteOriginAirport);
            }
        });

        autocompleteDestinationAirport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autocompleteDestinationAirport.setSelection(0); // cursor to start
                hideKeyboard(autocompleteDestinationAirport);
            }
        });

        /*  Αρχικοποίηση adapter. Αχρηστη.
        String[] airports = getResources().getStringArray(R.array.available_airports);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, airports);

        autocompleteOriginAirport.setAdapter(adapter);
        autocompleteDestinationAirport.setAdapter(adapter);
        autocompleteOriginAirport.setThreshold(1);
        autocompleteDestinationAirport.setThreshold(1);
        */

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


        autocompleteOriginAirport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                new AutocompleteTask().execute(s.toString());
            }
        });

        autocompleteDestinationAirport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new AutocompleteTask().execute(s.toString());
            }
        });

        setCurrentDateOnView();
        addListenerOnButton();

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

        currency = mCurrency;
        maxResults = mMaxResults;
        max_price = mMax_price;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.results_options, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                //refresh;
                Toast toast = Toast.makeText(this, "Nothing to refresh..." ,Toast.LENGTH_LONG);
                toast.show();
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


    /* Task for the autocomplete */
    public class AutocompleteTask extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            String[] respons = null;
            if (params.length == 0){
                return null;
            }
            URL autocompleteRequestURL = NetworkUtils.buildUrlForAutocomplete(params[0]);
            String jsonAutocompleteResponse = null;

            try {
                jsonAutocompleteResponse = NetworkUtils.getResponseFromHttpUrl(autocompleteRequestURL);
                respons = FlightsJsonUtils.autocompletAirport(jsonAutocompleteResponse);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return respons;
        }

        @Override
        protected void onPostExecute(String[] respons) {
            if (respons != null) {
                if (respons.length > 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, respons);

                    autocompleteOriginAirport.setAdapter(adapter);
                    autocompleteDestinationAirport.setAdapter(adapter);
                    autocompleteOriginAirport.setThreshold(1);
                    autocompleteDestinationAirport.setThreshold(1);


                  /*  autocompleteOriginAirport.setAdapter(adapter);
                    autocompleteDestinationAirport.setAdapter(adapter);
                    autocompleteOriginAirport.setThreshold(1);
                    autocompleteDestinationAirport.setThreshold(1);*/
                }
            }
        }
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private int persons = 1;

    /**
     * Just decrease the number of persons in flight.
     */
    public void decreasePerson(View view) {
        if (persons == 1) {
            Toast toast = Toast.makeText(this, "Minimum is 1 person", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        persons -= 1;
        displayPersons(persons);
    }


    /**
     * Just increase the number of persons in flight.
     */
    public void increasePerson(View view) {
        if (persons == 10) {
            Toast toast = Toast.makeText(this, "Maximum is 10 persons", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        persons += 1;
        displayPersons(persons);
    }


    /**
     * displays the new value of Persons
     */
    public void displayPersons(int i) {
        TextView n = (TextView) findViewById(R.id.persons_to_fly);
        n.setText("" + i);
    }


    /**
     *
     */
    public void showResults(View view){
        /* καλέι τα dummy δεδομένα

        startActivity(intent);
       */

        Intent intent = new Intent(this, ShowResultsActivity.class);
        intent.putExtra("origin", getOrigin());
        intent.putExtra("destination", getDestination());
        intent.putExtra("departure_date", getDepartureDate());
        intent.putExtra("return_date", getArrivalDate());
        intent.putExtra("is_one_way", Boolean.toString(isOneWay()));
        intent.putExtra("is_non_stop", Boolean.toString(isNonStop()));
        intent.putExtra("persons", Integer.toString(getPersons()));



        startActivity(intent);

    }

    /**
     * Προσθέτω listeners στα κουμπιά datepickers και καθαρισμός επιλογών.
     * Περιλαμβάνει και αναχώρηση και επιστροφή.
     */
    public void addListenerOnButton(){
        departure_date_button = (Button) findViewById(R.id.button_departure_date);
        departure_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DEPARTURE_DIALOG_ID);
            }
        });

        arrival_date_button = (Button) findViewById(R.id.button_arrival_date);
        arrival_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ARRIVAL_DIALOG_ID);
            }
        });

        ImageButton clearOrigin = (ImageButton) findViewById(R.id.clearOriginTextViewButton);
        clearOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autocompleteOriginAirport.setText("");
            }
        });

        ImageButton clearDestination = (ImageButton) findViewById(R.id.clearDestinationTextViewButton);
        clearDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autocompleteDestinationAirport.setText("");
            }
        });
    }

    public void setCurrentDateOnView(){

        display_departure_date = (TextView) findViewById(R.id.departure_date_text_view);
        display_arrival_date = (TextView) findViewById(R.id.arrival_date_text_view);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DEPARTURE_DIALOG_ID:
                // set date picker as current date for departure
               return new DatePickerDialog(this, datePickerDepartureListener,
                        year, month, day);
            case DATE_ARRIVAL_DIALOG_ID:
                if (departureHasSetted){
                    String[] date = departure_date_button.getText().toString().split("-");
                    day = Integer.valueOf(date[0]); month = Integer.valueOf(date[1]);  year = Integer.valueOf(date[2]); month--; day += 3;
                }
                // set date picker as current date for arrival
                return new DatePickerDialog(this, datePickerReturnListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerDepartureListener
            = new DatePickerDialog.OnDateSetListener(){
        // when dialog box is closed, below method will be called.

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // Format the date to become 2 digits.
            DecimalFormat mFormatter = new DecimalFormat("00");
            year_formatted = mFormatter.format(year);
            month_formatted = mFormatter.format(month+1);
            day_formatted = mFormatter.format(day);


            departure_date_button.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year));
            departure_date_formated = (new StringBuilder().append(year_formatted)
                    .append("-").append(month_formatted).append("-").append(day_formatted)).toString();

            departureHasSetted = true;

            /* Ημερομηνία επιστροφής 3 μέρες μετά */


            final Calendar c = Calendar.getInstance();

            month += 1;
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                if (day+3 > 31) {
                    month += 1;
                    day = 3 - (31 - day);
                }else {day += 3;}
            }
            else if (month == 4 || month == 6 || month == 9 || month == 11){
                if (day+3 > 30){
                    month += 1;
                    day = 3 - (30 - day);
                }else {day += 3;}
            }
            else if (month == 2 ){
                if (day+3 > 28){
                    month += 1;
                    day = 3 - (28 - day);
                }
                else {day += 3;}
            }
            else {
                day += 3;
            }

            year_formatted = mFormatter.format(year);
            month_formatted = mFormatter.format(month);
            day_formatted = mFormatter.format(day);

            arrival_date_button.setText(new StringBuilder().append(day)
                    .append("-").append(month).append("-").append(year));

            return_date_formated =  (new StringBuilder().append(year_formatted)
                    .append("-").append(month_formatted).append("-").append(day_formatted)).toString();

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerReturnListener
            = new DatePickerDialog.OnDateSetListener(){
        // when dialog box is closed, below method will be called.

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // Format the date to become 2 digits.
            DecimalFormat mFormatter = new DecimalFormat("00");
            year_formatted = mFormatter.format(year);
            month_formatted = mFormatter.format(month+1);
            day_formatted = mFormatter.format(day);


            arrival_date_button.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year));
            return_date_formated = (new StringBuilder().append(year_formatted)
                    .append("-").append(month_formatted).append("-").append(day_formatted)).toString();

            arrivalHasSetted = true;
        }
    };


    public String getOrigin(){
        return autocompleteOriginAirport.getText().toString();
    }

    public String getDestination(){
        return autocompleteDestinationAirport.getText().toString();
    }


    public String getDepartureDate(){
        return departure_date_formated;
        // mDepartureDateTextView.getText().toString();
    }

    public String getArrivalDate(){
        return return_date_formated;
                //mArrivalDateTextView.getText().toString();
    }

    public boolean isOneWay(){
        return mOneWayCheckbox.isChecked();
    }

    public boolean isNonStop(){
        return mDirectFlightCheckbox.isChecked();
    }

    public int getPersons(){
        return persons;
    }




}
