package com.test.weatherchecker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.test.weatherchecker.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static com.test.weatherchecker.utils.NetworkUtils.generateURL;
import static com.test.weatherchecker.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static TextView showApi;
    public static TextView description;
    public static TextView wind;
    public static TextView pressure;
    public static TextView city;
    public static TextView humidity;
    private TextView loading;
    public static ImageView icon;
    private Button pressf;
    private ProgressBar loadingIndicator;
    public LocationManager locationManager;
    public String lat = "54.8692";
    public String lon = "83.08880";
    private TextView error_message;



    private void showErrorMessage() {

        error_message.setVisibility(View.VISIBLE);
    }


    class WeatherQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            loadingIndicator.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);

        }

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;

            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;

        }

        @Override
        protected void onPostExecute(String response) {
            String descriptionText = null;
            String pressureValue = null;
            String filepath = null;
            int humidityValue = 0;
            String cityName = null;
            int windSpeed = 0;
            int temp = 0;



            if (response != null && !response.equals("")) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    JSONObject mintemp = jsonResponse.getJSONObject("main");
                    temp = mintemp.getInt("temp") - 273;

                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject weatherDesc = jsonArray.getJSONObject(0);
                    descriptionText = weatherDesc.getString("description");
                    JSONObject wind = jsonResponse.getJSONObject("wind");
                    //  JSONObject sunRiseSet = jsonResponse.getJSONObject("sys");
                    windSpeed = wind.getInt("speed");
                    //  sunset = sunRiseSet.getInt("sunset");//java.util.Date time = new java.util.Date((long)sunset*1000+21600*1000);
                    // sunset1 = String.valueOf(time);
                    filepath = weatherDesc.getString("icon");
                    pressureValue = mintemp.getString("pressure");
                    humidityValue = mintemp.getInt("humidity");
                    cityName = jsonResponse.getString("name");

                } catch (JSONException e) {

                    e.printStackTrace();

                }
                switch (descriptionText) {
                    case "fog":
                        description.setText("Туман");
                        break;
                    case "mist":
                        description.setText("Дымка");
                        break;
                    case "clear sky":
                        description.setText("Ясно");
                        break;
                    case "few clouds":
                        description.setText("Небольшая облачность");
                        break;
                    case "scattered clouds":
                        description.setText("Средняя облачность");
                        break;
                    case "broken clouds":
                        description.setText("Облачно");
                        break;
                    case "overcast clouds":
                        description.setText("Пасмурно");
                        break;
                    case "light rain":
                        description.setText("Небольшой дождь");
                        break;
                    case "shower rain":
                        description.setText("Ливень");
                        break;
                    case "thunderstorm":
                        description.setText("Шторм");
                        break;
                    default:
                        description.setText("" + descriptionText);
                }
                switch (filepath) {
                    case "01d":
                        icon.setImageResource(R.drawable.d01d);
                        break;
                    case "01n":
                        icon.setImageResource(R.drawable.d01n);
                        break;
                    case "02d":
                        icon.setImageResource(R.drawable.d02d);
                        break;
                    case "02n":
                        icon.setImageResource(R.drawable.d02n);
                        break;
                    case "03d":
                        icon.setImageResource(R.drawable.d03d);
                        break;
                    case "03n":
                        icon.setImageResource(R.drawable.d03n);
                        break;
                    case "04d":
                        icon.setImageResource(R.drawable.d04d);
                        break;
                    case "04n":
                        icon.setImageResource(R.drawable.d04n);
                        break;
                    case "09d":
                        icon.setImageResource(R.drawable.d09d);
                        break;
                    case "09n":
                        icon.setImageResource(R.drawable.d09n);
                        break;
                    case "10d":
                        icon.setImageResource(R.drawable.d10d);
                        break;
                    case "10n":
                        icon.setImageResource(R.drawable.d10n);
                        break;
                    case "11d":
                        icon.setImageResource(R.drawable.d11d);
                        break;
                    case "11n":
                        icon.setImageResource(R.drawable.d11n);
                        break;
                    case "13d":
                        icon.setImageResource(R.drawable.d13d);
                        break;
                    case "13n":
                        icon.setImageResource(R.drawable.d13n);
                        break;
                    case "50d":
                        icon.setImageResource(R.drawable.d50d);
                        break;
                    case "50n":
                        icon.setImageResource(R.drawable.d50n);
                        break;
                    default:
                        break;

                }


                showApi.setText(temp + " ℃");
                pressure.setText("" + pressureValue + " мм рт. ст.");
                humidity.setText("Влажность: " + humidityValue + " %");
                city.setText("" + cityName);
                wind.setText("Ветер: " + windSpeed + " м/c");
                error_message.setVisibility(View.INVISIBLE);;


            } else {
                showErrorMessage();
            }
            loadingIndicator.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        String lat = null; //"53.8692";
//        String lon = null; //"84.08880";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        error_message = findViewById(R.id.TV_errorMessage);
        loadingIndicator = findViewById(R.id.PB_progressbar);
        icon = findViewById(R.id.IV_icon);
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        pressf = findViewById(R.id.BT_pressf);
        showApi = findViewById(R.id.TV_main);
        loading = findViewById(R.id.TV_loading);
        wind = findViewById(R.id.TV_wind);
        description = findViewById(R.id.TV_description);
        city = findViewById(R.id.TV_city);
        pressure = findViewById(R.id.TV_pressure);
        humidity = findViewById(R.id.TV_humidity);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        };
        pressf.setOnClickListener(onClickListener);

        new WeatherQueryTask().execute(generateURL(lat, lon));

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            return;
        }

        LocationListener listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                locationManager.removeUpdates(this);
                if (location == null) {

                    return; }

                formatLocation(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, listener);

    }

    public void formatLocation(Location location) {

        if (location == null)
            return;
        lat = String.format("%1$.4f",location.getLatitude());
        lon = String.format("%1$.4f",location.getLongitude());
     //   Log.v(TAG, lat);
        return;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Отменяем анимацию обновления
                mSwipeRefreshLayout.setRefreshing(false);


            }
        }, 1000);
    }
    @Override
    public void onResume() {
        super.onResume();
        new WeatherQueryTask().execute(generateURL(lat,lon));
    }
}

