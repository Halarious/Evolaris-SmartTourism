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

        if(userLocation != null)
        {
            String latitude = String.valueOf(userLocation.getLatitude());
            String longitude = String.valueOf(userLocation.getLongitude());

            Latches.getLatch().setFetchCountDownLatch(2);

            locationDataLoader.getMuseums(latitude, longitude);
            weatherDataLoader.getWeather(latitude, longitude);

            try {
                Latches.getLatch().fetchCountDownLatch.await();
                TimeIntermediaryResult.timePoint = new TimePoint();
                returnMessage = "Success!";
            } catch (InterruptedException e) {
                returnMessage = "Interrupt exception!";
                e.printStackTrace();
            }
        }
        else
        {
            returnMessage = "User location cannot be retrieved!";
        }

        //TODO(rob): Remove this eventually
        if (TestHandle.progressDialog != null) {
            TestHandle.progressDialog.dismiss();
        }

        return returnMessage;
    }

    @Override
    protected void onPostExecute(String returnMessage)
    {
        switch(returnMessage)
        {
            case "Success!":
            {
                List<hr.evolaris.air.foi.evolaris_smarttourism.c_location.Location> locationList =
                        LocationIntermediaryResult.locationList.results;
                if(locationList.size() > 0 && LocationIntermediaryResult.locationList.status == "OK")
                {

                }

            } break;

            case "Interrupt Exception":
            {

            } break;

            case "User location cannot be retrieved!":
            {

            } break;

            default:

        }

        super.onPostExecute(returnMessage);
    }
}
