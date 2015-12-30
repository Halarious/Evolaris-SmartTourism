package hr.evolaris.air.foi.evolaris_smarttourism.db;

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
    private int interval;
    private int priority;

    public String lastUpdateTime;
    public Location currentLocation;

    private CurrentLocation()
    {
        interval = 10000;
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY;

        createLocationRequest();
    }

    public static CurrentLocation getInstance()
    {
        if(instance == null)
        {
            instance = new CurrentLocation();
        }
        return instance;
    }


    @Override
    public void onLocationChanged(Location location)
    {
        currentLocation = location;
        lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
    }

    private void createLocationRequest()
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(interval/3);
        locationRequest.setPriority(priority);
    }

    public void startLocationUpdated(GoogleApiClient mGoogleApiClient)
    {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
    }

    public void stopLocationUpdates(GoogleApiClient mGoogleApiClient)
    {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    public void changeInterval(GoogleApiClient mGoogleApiClient, int newInterval)
    {
        locationRequest.setInterval(newInterval);
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
    }

    public void changePriority(GoogleApiClient mGoogleApiClient, int newPriority)
    {
        locationRequest.setPriority(newPriority);
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
    }
}
