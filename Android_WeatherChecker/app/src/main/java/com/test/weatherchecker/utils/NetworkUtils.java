package com.test.weatherchecker.utils;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import static android.content.ContentValues.TAG;
import android.bluetooth.le.ScanCallback;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.location.LocationListener;
import android.location.*;
import com.test.weatherchecker.MainActivity;

// http://api.openweathermap.org/data/2.5/weather?q=Novosibirsk&appid=d652ae3805ef7b9cb4f80b6a4702f2c3
public class NetworkUtils extends AppCompatActivity {

    private static final String WEATHER_API_BASE_URL = "http://api.openweathermap.org/";
    private static final String WEATHER_WEATHER_GET = "data/2.5/weather";
    private static final String PARAM = "q";
    private static final String PARAM_APPID = "appid";
    private static final String PARAM_lat = "lat";
    private static final String PARAM_lon = "lon";


    public static URL generateURL(String lat, String lon){







        Uri builtUri = Uri.parse(WEATHER_API_BASE_URL+WEATHER_WEATHER_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_lat,lat)
                .appendQueryParameter(PARAM_lon,lon)
        //        .appendQueryParameter(PARAM, "Novosibirsk")
                .appendQueryParameter(PARAM_APPID,"d652ae3805ef7b9cb4f80b6a4702f2c3")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasNext = scanner.hasNext();
            if (hasNext) {
                return scanner.next();
            }
            else {
                return null;
            }
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }

}
