package hr.evolaris.air.foi.evolaris_smarttourism;

import android.os.AsyncTask;

import java.util.concurrent.CountDownLatch;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.db.Latch;

public class AsyncCollectInfo extends AsyncTask<String, Integer, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        final LocationDataLoader locationDataLoader;
        final WeatherDataLoader weatherDataLoader;
        locationDataLoader = new LocationDataLoader();
        weatherDataLoader = new WeatherDataLoader();

        Latch.getLatch().setCountDownLatch(2);

        locationDataLoader.getMuseums("46.3053777", "16.3328265");
        weatherDataLoader.getWeather("46.3053777", "16.3328265");

        try
        {
            Latch.getLatch().countDownLatch.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {

        super.onPostExecute(s);
    }
}
