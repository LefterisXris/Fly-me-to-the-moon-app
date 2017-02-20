package com.example.android.flymetothemoon;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class SearchResultsActivity extends AppCompatActivity {

    ArrayAdapter<String> mForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        int aa = 1;
        ArrayList<Result> results = new ArrayList<Result>();

        results.add(new Result("Aegean", "45AE65", "13:25", "15:40", 155, aa++));
        results.add(new Result("Ryanair", "6e9dfs", "15:10", "17:00", 114, aa++));
        results.add(new Result("Ellinair", "8ffds8", "10:00", "12:40", 137, aa++));
        results.add(new Result("Germanwings", "165Ae5", "16:30", "17:50", 169, aa++));
        results.add(new Result("Emirates", "wref55", "06:10", "08:10", 212, aa++));
        results.add(new Result("olympicAir", "g66f66", "20:25", "22:45", 360, aa++));
        results.add(new Result("Aegean", "45AE65", "13:25", "15:40", 155, aa++));
        results.add(new Result("Ryanair", "6e9dfs", "15:10", "17:00", 114, aa++));
        results.add(new Result("Ellinair", "8ffds8", "10:00", "12:40", 137, aa++));
        results.add(new Result("Germanwings", "165Ae5", "16:30", "17:50", 169, aa++));
        results.add(new Result("Emirates", "wref55", "06:10", "08:10", 212, aa++));
        results.add(new Result("olympicAir", "g66f66", "20:25", "22:45", 360, aa++));
        results.add(new Result("Aegean", "45AE65", "13:25", "15:40", 155, aa++));
        results.add(new Result("Ryanair", "6e9dfs", "15:10", "17:00", 114, aa++));
        results.add(new Result("Ellinair", "8ffds8", "10:00", "12:40", 137, aa++));
        results.add(new Result("Germanwings", "165Ae5", "16:30", "17:50", 169, aa++));
        results.add(new Result("Emirates", "wref55", "06:10", "08:10", 212, aa++));
        results.add(new Result("olympicAir", "g66f66", "20:25", "22:45", 360, aa++));
        results.add(new Result("Aegean", "45AE65", "13:25", "15:40", 155, aa++));
        results.add(new Result("Ryanair", "6e9dfs", "15:10", "17:00", 114, aa++));
        results.add(new Result("Ellinair", "8ffds8", "10:00", "12:40", 137, aa++));
        results.add(new Result("Germanwings", "165Ae5", "16:30", "17:50", 169, aa++));
        results.add(new Result("Emirates", "wref55", "06:10", "08:10", 212, aa++));
        results.add(new Result("olympicAir", "g66f66", "20:25", "22:45", 360, aa++));
        results.add(new Result("Aegean", "45AE65", "13:25", "15:40", 155, aa++));
        results.add(new Result("Ryanair", "6e9dfs", "15:10", "17:00", 114, aa++));
        results.add(new Result("Ellinair", "8ffds8", "10:00", "12:40", 137, aa++));
        results.add(new Result("Germanwings", "165Ae5", "16:30", "17:50", 169, aa++));
        results.add(new Result("Emirates", "wref55", "06:10", "08:10", 212, aa++));
        results.add(new Result("olympicAir", "g66f66", "20:25", "22:45", 360, aa++));
        results.add(new Result("Aegean", "45AE65", "13:25", "15:40", 155, aa++));
        results.add(new Result("Ryanair", "6e9dfs", "15:10", "17:00", 114, aa++));
        results.add(new Result("Ellinair", "8ffds8", "10:00", "12:40", 137, aa++));
        results.add(new Result("Germanwings", "165Ae5", "16:30", "17:50", 169, aa++));
        results.add(new Result("Emirates", "wref55", "06:10", "08:10", 212, aa++));
        results.add(new Result("olympicAir", "g66f66", "20:25", "22:45", 360, aa++));


      /* ResultAdapter resultAdapter= new ResultAdapter(this, results);
       ListView listView = (ListView) findViewById(R.id.activity_search_results);

       listView.setAdapter(resultAdapter);
    */   // (String company, String flight_code, String dep_time, String arr_time, double price)

        /*
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            //MODIFIED FOR CITY OF THESSALONIKI, GREECE
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?id=734077&mode=json&units=metric&cnt=7");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine() != null)) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empy. No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();

        }catch (IOException e){
                Log.e("PlaceholderFragment", "Error", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
            } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try{
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        return rootView;*/

    }

    public class FetchWeatherTask extends AsyncTask<String,Void,String[]> {

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        /* The date/time conversion code is going to be moved outside the asynctask later,
         * so for convenience we're breaking it out into its own method now.
         */
        private String getReadableDateString(long time){
            // Because the API returns a unix timestamp (measured in seconds),
            // it must be converted to milliseconds in order to be converted to valid date.
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(time);
        }

        /**
         * Prepare the weather high/lows for presentation.
         */
        private String formatHighLows(double high, double low) {
            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        /**
         * Take the String representing the complete forecast in JSON Format and
         * pull out the data we need to construct the Strings needed for the wireframes.
         *
         * Fortunately parsing is easy:  constructor takes the JSON string and converts it
         * into an Object hierarchy for us.
         */
        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAX = "max";
            final String OWM_MIN = "min";
            final String OWM_DESCRIPTION = "main";

            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            Time dayTime = new Time();
            dayTime.setToNow();

            // we start at the day returned by local time. Otherwise this is a mess.
            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

            // now we work exclusively in UTC
            dayTime = new Time();

            String[] resultStrs = new String[numDays];
            for(int i = 0; i < weatherArray.length(); i++) {
                // For now, using the format "Day, description, hi/low"
                String day;
                String description;
                String highAndLow;

                // Get the JSON object representing the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);

                // The date/time is returned as a long.  We need to convert that
                // into something human-readable, since most people won't read "1400356800" as
                // "this saturday".
                long dateTime;
                // Cheating to convert this to UTC time, which is what we want anyhow
                dateTime = dayTime.setJulianDay(julianStartDay+i);
                day = getReadableDateString(dateTime);

                // description is in a child array called "weather", which is 1 element long.
                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                description = weatherObject.getString(OWM_DESCRIPTION);

                // Temperatures are in a child object called "temp".  Try not to name variables
                // "temp" when working with temperature.  It confuses everybody.
                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
                double high = temperatureObject.getDouble(OWM_MAX);
                double low = temperatureObject.getDouble(OWM_MIN);

                highAndLow = formatHighLows(high, low);
                resultStrs[i] = day + " - " + description + " - " + highAndLow;
            }

            for (String s : resultStrs) {
                Log.v(LOG_TAG, "Forecast entry: " + s);
            }
            return resultStrs;

        }

        @Override
        protected String[] doInBackground(String... params) {

            // If there's no city code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;
            String weatherFormat = "json";
            int numDays = 7;
            String units = "metric";

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                //MODIFIED FOR CITY OF THESSALONIKI, GREECE
                final String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?";
                //"id=734077&mode=json&units=metric&cnt=7";
                final String queryParam = "id";
                final String formatParam= "mode";
                final String unitsParam= "units";
                final String daysParam = "cnt";
                final String apiKeyParam = "APPID";
                final String appIdValue = "27949ea6b6dffa1dad1deb925c9b024b";

                Uri builtUri = Uri.parse(baseUrl).buildUpon()
                        .appendQueryParameter(queryParam,params[0])
                        .appendQueryParameter(formatParam,weatherFormat)
                        .appendQueryParameter(unitsParam, units)
                        .appendQueryParameter(daysParam, Integer.toString(numDays))
                        .appendQueryParameter(apiKeyParam, appIdValue)
                        .build();

                URL url = new URL(builtUri.toString());

                Log.v(LOG_TAG, "Built URI: "+builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                Log.v(LOG_TAG,"Forecast JSON String: "+forecastJsonStr);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getWeatherDataFromJson(forecastJsonStr, numDays);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                mForecastAdapter.clear();
                for(String dayForecastStr : result) {
                    mForecastAdapter.add(dayForecastStr);
                }
                // New data is back from the server.  Hooray!
            }
        }
    }


/*
    private class FetchFlights extends AsyncTask<URL, Integer, Long> {
        @Override
        protected Long doInBackground(URL... params) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }
    }*/

}
