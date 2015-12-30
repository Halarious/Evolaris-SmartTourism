package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import android.widget.TextView;

import java.util.concurrent.CountDownLatch;

import hr.evolaris.air.foi.evolaris_smarttourism.db.Latch;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherDataLoader
{
    private static String APIKey = "2de143494c0b295cca9337e1e96b00e0";
    private static String APIUrl = "http://api.openweathermap.org";

    private WebServiceCall webServiceCall;

    public WeatherDataLoader()
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(APIUrl).build();

        webServiceCall = restAdapter.create(WebServiceCall.class);
    }

    private Callback<Weather> BaseCallback = new Callback<Weather>()
    {

        @Override
        public void success(Weather weather, Response response)
        {
            Latch.getLatch().countDownLatch.countDown();

        }

        @Override
        public void failure(RetrofitError error)
        {
            Latch.getLatch().countDownLatch.countDown();

            error.printStackTrace();
        }
    };

    public void getWeather(String latitude, String longitude)
    {
        webServiceCall.getWeather( latitude, longitude,
                                    APIKey, BaseCallback);
    }
}

