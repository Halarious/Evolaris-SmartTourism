package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import hr.evolaris.air.foi.evolaris_smarttourism.db.Latches;
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
            WeatherIntermediaryResult.response = response;
            WeatherIntermediaryResult.weather = weather;

            Latches.getLatch().fetchCountDownLatch.countDown();
        }

        @Override
        public void failure(RetrofitError error)
        {
            Latches.getLatch().fetchCountDownLatch.countDown();

            error.printStackTrace();
        }
    };

    public void getWeather(String latitude, String longitude)
    {
        WeatherIntermediaryResult.weather = null;
        WeatherIntermediaryResult.response = null;
        webServiceCall.getWeather( latitude, longitude,
                                    APIKey, BaseCallback);
    }
}

