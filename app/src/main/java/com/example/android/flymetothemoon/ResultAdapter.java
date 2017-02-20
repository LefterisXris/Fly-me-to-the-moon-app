package com.example.android.flymetothemoon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.flymetothemoon.FlightsDoc.FlightResults;
import com.example.android.flymetothemoon.FlightsDoc.Itineraries;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.flymetothemoon.R.drawable.currency;
import static com.example.android.flymetothemoon.R.drawable.max_price;

/**
 * Created by tsita on 10/1/2017.
 */

public class ResultAdapter extends ArrayAdapter<FlightResults> {

    // logo
   // private ImageView mCompanyLogoImageView;
    private TextView mCompanyNameTextView;

    // Outbound
    private TextView mOutboundDepartureTimeTextView;
    private TextView mOutboundDepartureAirportTextView;
    private TextView mOutboundDurationTextView;
    private TextView mOutboundStopTextView;
    private TextView mOutboundArrivalTimeTextView;
    private TextView mOutboundArrivalAirportTextView;

    // Inbound
    private TextView mInboundDepartureTimeTextView;
    private TextView mInboundDepartureAirportTextView;
    private TextView mInboundDurationTextView;
    private TextView mInboundStopTextView;
    private TextView mInboundArrivalTimeTextView;
    private TextView mInboundArrivalAirportTextView;

    //price
    private TextView mTotalPriceTextView;

    public ResultAdapter(Activity context, ArrayList<FlightResults> result) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, result);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.result_item, parent, false);
        }


        FlightResults currentFlight = getItem(position);

        mCompanyNameTextView = (TextView) listItemView.findViewById(R.id.company_name_text_view);

        //outbound
        mOutboundDepartureTimeTextView = (TextView) listItemView.findViewById(R.id.outbound_departure_time_text_view);
        mOutboundDepartureAirportTextView = (TextView) listItemView.findViewById(R.id.outbound_departure_airport_text_view);
        mOutboundDurationTextView = (TextView) listItemView.findViewById(R.id.outbound_duration_text_view);
      //  mOutboundStopTextView = (TextView) listItemView.findViewById(R.id.outbound_stop_text_view);
        mOutboundArrivalTimeTextView = (TextView) listItemView.findViewById(R.id.outbound_arrival_time_text_view);
        mOutboundArrivalAirportTextView = (TextView) listItemView.findViewById(R.id.outbound_arrival_airport_text_view);

        //inbound
        mInboundDepartureTimeTextView = (TextView) listItemView.findViewById(R.id.inbound_departure_time_text_view);
        mInboundDepartureAirportTextView = (TextView) listItemView.findViewById(R.id.inbound_departure_airport_text_view);
        mInboundDurationTextView = (TextView) listItemView.findViewById(R.id.inbound_duration_text_view);
        //mInboundStopTextView = (TextView) listItemView.findViewById(R.id.inbound_stop_text_view);
        mInboundArrivalTimeTextView = (TextView) listItemView.findViewById(R.id.inbound_arrival_time_text_view);
        mInboundArrivalAirportTextView = (TextView) listItemView.findViewById(R.id.inbound_arrival_airport_text_vie);

        //price
        mTotalPriceTextView = (TextView) listItemView.findViewById(R.id.total_price_text_view);



        // Ανάθεση τιμών!

        mCompanyNameTextView.setText(currentFlight.getItineraries().get(0).getOutbound().get(0).getAirline_full_name());

        mOutboundDepartureTimeTextView.setText(currentFlight.getItineraries().get(0).getOutbound().get(0).getDeparture_time());
        mOutboundDepartureAirportTextView.setText(currentFlight.getItineraries().get(0).getOutbound().get(0).getOrigin_airport());
        mOutboundDurationTextView.setText(currentFlight.getItineraries().get(0).getOutbound().get(0).getDuration());
        mOutboundArrivalTimeTextView.setText(currentFlight.getItineraries().get(0).getOutbound().get(0).getArrival_time());
        mOutboundArrivalAirportTextView.setText(currentFlight.getItineraries().get(0).getOutbound().get(0).getDestination_airport());

        mInboundDepartureTimeTextView.setText(currentFlight.getItineraries().get(0).getInbounds().get(0).getDeparture_time());
        mInboundDepartureAirportTextView.setText(currentFlight.getItineraries().get(0).getInbounds().get(0).getOrigin_airport());
        mInboundDurationTextView.setText(currentFlight.getItineraries().get(0).getInbounds().get(0).getDuration());
        mInboundArrivalTimeTextView.setText(currentFlight.getItineraries().get(0).getInbounds().get(0).getArrival_time());
        mInboundArrivalAirportTextView.setText(currentFlight.getItineraries().get(0).getInbounds().get(0).getDestination_airport());

        //TODO επιλογή απο options.

        mTotalPriceTextView.setText(currentFlight.getPrice());  // + " €"


        return listItemView;
    }
}


