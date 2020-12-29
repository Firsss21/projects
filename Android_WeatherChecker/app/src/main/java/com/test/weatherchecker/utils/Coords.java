//package com.test.weatherchecker.utils;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import com.test.weatherchecker.MainActivity;
//
//import java.util.Date;
//
//import static android.content.ContentValues.TAG;
//import static com.test.weatherchecker.MainActivity.humidity;
//
//
//public class Coords extends AppCompatActivity {
//
//    public String lat = null; //"53.8692";
//    public String lon = null; //"84.08880";
//
//
//    public String coordLatLon() {
//
//
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, new LocationListener() {
//
//            @Override
//            public void onLocationChanged(Location location) {
//                Log.v(TAG, "AGAIN BLUAAAAAAAAAAAAAAATb");
//                locationManager.removeUpdates(this);
//                if (location == null)
//                    return;
//                // String gps = String.format("%1$.2f,%2$.2f, time %3$tT", location.getLatitude(), location.getLongitude(), new Date(location.getTime())) ;
//                formatLocation(location);
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//            }
//        });
//
//        return null;
//    }
//
//    public void formatLocation(Location location) {
//
//        if (location == null)
//            return;
//        this.lat = String.format("%1$.4f",location.getLatitude());
//        this.lon = String.format("%1$.4f",location.getLongitude());
//        humidity.setText("lat = "+lat+", lon = "+lon);
//        return;
//    }
//
//
//}
//
