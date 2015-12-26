package hr.evolaris.air.foi.evolaris_smarttourism.db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

public class CurrentLocation implements LocationListener
{
    private static CurrentLocation instance;
    private static LocationRequest locationRequest;
        public static String lastUpdateTime;
    public static Location currentLocation;

    private CurrentLocation()
    {
        createLocationRequest();
    }

    private void  createLocationRequest()
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public static CurrentLocation getInstance()
    {
        if(instance == null)
        {
            instance = new CurrentLocation();
        }
        return instance;
    }

    public void startLocationUpdated(GoogleApiClient mGoogleApiClient)
    {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
    }

    public void stopLocationUpdates(GoogleApiClient mGoogleApiClient)
    {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }


    @Override
    public void onLocationChanged(Location location)
    {
        currentLocation = location;
        lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
    }
}
