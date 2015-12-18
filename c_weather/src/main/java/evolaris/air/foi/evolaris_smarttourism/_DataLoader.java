package evolaris.air.foi.evolaris_smarttourism;

import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class _DataLoader
{
    private static String APIKey = "2de143494c0b295cca9337e1e96b00e0";
    private static String APIUrl = "http://api.openweathermap.org";

    private TextView View;
    private WebServiceCall webServiceCall;

    public _DataLoader()
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(APIUrl).build();

        WebServiceCall webServiceCall = restAdapter.create(WebServiceCall.class);

    }

    private Callback<Weather> BaseCallback = new Callback<Weather>()
    {

        @Override
        public void success(Weather weather, Response response)
        {
            View.setText("Success!");
        }

        @Override
        public void failure(RetrofitError error)
        {
            View.setText("Failed!");
        }
    };

    public void getWeather(TextView View)
    {
        this.View = View;
        webServiceCall.getWeather( "46.3053777", "16.3328265",
                                    APIKey, BaseCallback);
    }
}

