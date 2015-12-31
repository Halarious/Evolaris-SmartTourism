package hr.evolaris.air.foi.evolaris_smarttourism;

import android.location.Location;
import android.os.AsyncTask;

import java.util.List;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_time.TimeIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_time.TimePoint;
import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.db.Latches;

public class AsyncCollectInfo extends AsyncTask<Location, Integer, String>
{
    @Override
    protected String doInBackground(Location... params)
    {
        String returnMessage;

        final Location userLocation = UserLocationManager.getInstance().currentLocation;
        final LocationDataLoader locationDataLoader;
        final WeatherDataLoader weatherDataLoader;
        locationDataLoader = new LocationDataLoader();
        weatherDataLoader = new WeatherDataLoader();

        String latitude = String.valueOf(userLocation.getLatitude());
        String longitude = String.valueOf(userLocation.getLongitude());

        Latches.getLatch().setCountDownLatch(2);

        locationDataLoader.getMuseums(latitude, longitude);
        weatherDataLoader.getWeather(latitude, longitude);

        try
        {
            Latches.getLatch().countDownLatch.await();
            TimeIntermediaryResult.timePoint = new TimePoint();
            returnMessage = "Success!";
        }
        catch (InterruptedException e)
        {
            returnMessage = "Interrupt exception!";
            e.printStackTrace();
        }

        TestHandle.progressDialog.dismiss();

        return returnMessage;
    }

    @Override
    protected void onPostExecute(String s)
    {
        List<hr.evolaris.air.foi.evolaris_smarttourism.c_location.Location> locationList =
                LocationIntermediaryResult.locationList.results;
        if(locationList.size() > 0 && LocationIntermediaryResult.locationList.status == "OK")
        {

        }
        super.onPostExecute(s);
    }
}