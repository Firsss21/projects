package com.test.gpscheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Date;
import java.util.Date;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity {
    int i = 0;
    double lat = 0;
    float meters = 0;
    double lon = 0;
    double speed = 0;
    double minKm = 0;
    float[] results = new float[1];
    String lat1 = null;
    String lon1 = null;

    TextView tvTittlenet;

    TextView tvTittleGps;
    Button Start;
    Chronometer chronometer;
    LocationManager locationManager;
    boolean timerOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Start = findViewById(R.id.BT_start);
        chronometer = findViewById(R.id.CHR_chronometr);
        chronometer.stop();


        tvTittleGps = findViewById(R.id.tvTitleGPS);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

            return;
        }

        //     locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener, null);
        //  locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener, null);


        final LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                //  locationManager.removeUpdates(this);
                if (location == null)
                    return;
                formatLocation(location);
                //    tvLocationGPS.setText(""+gps+"\n"+lat+"\n"+lon);
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
      //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 15, listener);


        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerOn == false) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    timerOn = true;
                    meters = 0;
                    speed = 0;
                    minKm = 0;
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                  //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 15, listener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 15, listener);

                    Start.setText("Stop");
                }
                else {
                    chronometer.stop();
                    timerOn = false;
                    locationManager.removeUpdates(listener);
                    Start.setText("Start");


                }
            }
        });

//        double latitude=lat;
//        double longitude=lng;
//        float distance=0;
//        Location crntLocation=new Location("crntlocation");
//        crntLocation.setLatitude(currentLatitude);
//        crntLocation.setLongitude(currentLongitude);
//
//        Location newLocation=new Location("newlocation");
//        newLocation.setLatitude(latitude);
//        newLocation.setLongitude(longitude);
//        public String lat = "54.8692";
//        public String lon = "83.08880";


//        Location.distanceBetween(0, 0, 54.767934, 83.090693, results);
//        tvTittleGps.setText(""+(results[0]));

////float distance = crntLocation.distanceTo(newLocation);  in meters
//        distance =crntLocation.distanceTo(newLocation) / 1000; // in km

    }



    public void formatLocation(Location location) {
        if (location.getAccuracy()>15) {
            return;
        }

        if (lat != 0) {
            addLocation(location.getLatitude(),location.getLongitude(), location);
        }
        else {
            lat = location.getLatitude();
            lon = location.getLongitude();
            return;
        }
        return;
    }

    public void addLocation(double lat, double lon, Location location)
    {
        Location.distanceBetween(lat, lon, this.lat, this.lon, results);
        this.lat = lat;
        this.lon = lon;
        meters+=results[0];

        long tMills = (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000;
        speed = (double) ((meters/tMills)*3.6);
        minKm = (double) 60/((meters/tMills)*3.6);
     //   String metersString = String.format("Meters = %1$.0f \nSpeed = %2$.2d км/чf\nmin/km =%3$.2d мин/км",meters,speed,minKm);
        String metersString = String.format("Дистанция = %1$.1f метров\nСкорость = %2$.1f км/ч\n%3$.1f минут на км",meters,speed,minKm);
        tvTittleGps.setText(""+metersString);


    }


//    public void mCounter(float results) {
////
////    }


//    private LocationListener locationListener = new LocationListener() {
//
//        @Override
//        public void onLocationChanged(Location location) {
//            if (location == null)
//                return;
//            String gps = String.format("%1$.2f,%2$.2f, time %3$tT", location.getLatitude(), location.getLongitude(), new Date(location.getTime())) ;
//            tvLocationGPS.setText(""+gps);
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//        }
//    };


}