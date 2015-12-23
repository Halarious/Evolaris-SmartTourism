package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherDataLoader
{
    private static String APIKey = "2de143494c0b295cca9337e1e96b00e0";
    private static String APIUrl = "http://api.openweathermap.org";

    private TextView View;
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
            View.setText("Success!" + "\n" + weather.cod);
        }

        @Override
        public void failure(RetrofitError error)
        {
            View.setText("Failed!");

            error.printStackTrace();
        }
    };

    public void getWeather(TextView View)
    {
        this.View = View;
        webServiceCall.getWeather( "46.3053777", "16.3328265",
                                    APIKey, BaseCallback);
    }
}

