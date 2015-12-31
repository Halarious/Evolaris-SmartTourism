package hr.evolaris.air.foi.evolaris_smarttourism;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

public class UserLocationManager implements LocationListener
{
    private static UserLocationManager instance;
    private static LocationRequest locationRequest;
    private int interval;
    private int priority;
    private float minDisplacement;
    public String lastUpdateTime;
    public Location previousLocation;
    public Location currentLocation;

    private UserLocationManager()
    {
        interval = 10000;
        minDisplacement = 100;
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY;

        createLocationRequest();
    }

    public static UserLocationManager getInstance()
    {
        if(instance == null)
        {
            instance = new UserLocationManager();
        }
        return instance;
    }


    @Override
    public void onLocationChanged(Location location)
    {
        previousLocation = currentLocation;
        currentLocation = location;
        lastUpdateTime = DateFormat.getTimeInstance().format(new Date());

        //new AsyncCollectInfo().execute();
    }

    private void createLocationRequest()
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(interval);
        locationRequest.setFastestInterval(interval / 3);
        locationRequest.setPriority(priority);
        locationRequest.setSmallestDisplacement(minDisplacement);
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
