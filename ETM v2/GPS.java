package com.example.dell.etmv2;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GPS extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private final String TAG = "GaneshTestApp";
    private String lat;
    private String lon;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    Intent permission;


    @Override
    public void onCreate() {
        // service is being created
        super.onCreate();
        //setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate() Entered", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        // service is starting due to call to startService()
        // super.onStartCommand(Intent i,int 1, int 1);
        mGoogleApiClient.connect();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        mGoogleApiClient.disconnect();
        //super.onStop();

    }


    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        // 10,000 for every 10 seconds
        mLocationRequest.setInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permission = new Intent(this, PermissionsActivity.class);
            startActivity(permission);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "GoogleApiClient connection has been suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "GoogleApiClient connection has failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        //new GPSTask(this).execute(location);
        Log.i(TAG, location.toString());
        lat=(Double.toString(location.getLatitude()));
        lon=(Double.toString(location.getLongitude()));
        Toast.makeText(this,"Latitude:"+lat+"\n Longitude:"+lon,Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

