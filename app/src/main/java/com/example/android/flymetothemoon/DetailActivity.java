package com.example.android.flymetothemoon;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;
import static com.example.android.flymetothemoon.R.drawable.max_price;
import static com.example.android.flymetothemoon.R.string.departure_date;
import static com.example.android.flymetothemoon.R.string.destination;
import static com.example.android.flymetothemoon.R.string.origin;

public class DetailActivity extends AppCompatActivity {

    private String airlineName;
    private String go_fl_number;
    private String go_dep_time;
    private String go_arr_time;
    private String go_dur;
    private String ret_fl_number;
    private String ret_dep_time;
    private String ret_arr_time;
    private String ret_dur;
    private String price;

    private String origin_airport;
    private String return_airport;
    private String origin_date;
    private String return_date;


    private TextView goDepartureTime;
    private TextView goArrivalTime;
    private TextView goFlightNumber;
    private TextView goDuration;
    private TextView returnDepartureTime;
    private TextView returnArrivalTime;
    private TextView returnFlighNumber;
    private TextView returnDuration;
    private TextView priceTextView;
    private ImageView logo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            airlineName = extras.getString("name");
            go_fl_number = extras.getString("outbound_flight_number");
            go_dep_time = extras.getString("go_dep_time");
            go_arr_time = extras.getString("go_arr_time");
            go_dur = extras.getString("go_dur");
            ret_fl_number = extras.getString("inbound_flight_number");
            ret_dep_time = extras.getString("ret_dep_time");
            ret_arr_time = extras.getString("ret_arr_time");
            ret_dur = extras.getString("ret_dur");
            price = extras.getString("price");

            origin_airport = extras.getString("origin_airport");
            return_airport = extras.getString("return_airport");
            origin_date = extras.getString("origin_date");
            return_date = extras.getString("return_date");

            /*
                intent.putExtra("origin_airport", origin_airport);
                intent.putExtra("return_airport", return_airport);
                intent.putExtra("origin_date", origin_date);
                intent.putExtra("return_date", return_date);
             */

        }

        setContentView(R.layout.activity_detail);

        goDepartureTime = (TextView)findViewById(R.id.det_go_departure_time_textview);
        goArrivalTime = (TextView)findViewById(R.id.det_go_arrival_time_textview);
        goFlightNumber = (TextView)findViewById(R.id.det_go_flight_number_textview);
        goDuration = (TextView)findViewById(R.id.det_go_duration_textview);

        returnDepartureTime = (TextView)findViewById(R.id.det_return_departure_time_textview);
        returnArrivalTime = (TextView)findViewById(R.id.det_return_arrival_time_textview);
        returnFlighNumber = (TextView)findViewById(R.id.det_return_flight_number_textview);
        returnDuration = (TextView)findViewById(R.id.det_return_duration_textview);

        priceTextView = (TextView)findViewById(R.id.det_price_textview);

        logo = (ImageView)findViewById(R.id.det_airline_logo_image_view);


        // ΑΝΑΘΕΣΗ ΤΙΜΩΝ
        goDepartureTime.setText(go_dep_time);
        goArrivalTime.setText(go_arr_time);
        goFlightNumber.setText(go_fl_number);
        goDuration.setText(go_dur);

        returnDepartureTime.setText(ret_dep_time);
        returnArrivalTime.setText(ret_arr_time);
        returnFlighNumber.setText(ret_fl_number);
        returnDuration.setText(ret_dur);

        priceTextView.setText(price);


        switch (airlineName){
            case "Ryanair":
                logo.setImageResource(R.drawable.ryanair_logo);
                break;
            case "Aegean Airlines":
                logo.setImageResource(R.drawable.aegean_logo);
                break;
            case "Turkish Airlines":
                logo.setImageResource(R.drawable.turkish_logo);
            default:
                logo.setImageResource(R.drawable.no_image_available);
                break;
        }

    }

    public void shareDetails(){
        // Create the text message with a string
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        String msg = "";
        msg += "Πτήση από " + origin_airport + " για " + return_airport + "\n";
        msg += "στις " + origin_date + " με την εταιρία " + airlineName + "\n";
        msg += "με ώρα αναχώρησης: " + go_dep_time + " και ώρα άφιξης: " + go_arr_time + " (διάρκεια " + go_dur +")\n\n";
        msg += "Επιστροφή από " + return_airport + " για " + origin_airport + "\n";
        msg += "στις " + return_date + " με την εταιρία " + airlineName + "\n";
        msg += "με ώρα αναχώρησης: " + ret_dep_time + " και ώρα άφιξης: " + ret_arr_time + " (διάρκεια " + ret_dur +")\n\n";
        msg += "\t\t\t ΤΙΜΗ: " + price + ".";

        intent.putExtra(Intent.EXTRA_TEXT, msg);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Πτήση από την εφαρμογή Fly me to the moon!");


        // Verify that the intent will resolve to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share_options:
                //share;
                shareDetails();
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
}
