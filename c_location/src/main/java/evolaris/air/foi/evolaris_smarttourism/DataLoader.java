package evolaris.air.foi.evolaris_smarttourism;

import retrofit.RestAdapter;

public class DataLoader
{
    private String APIUrl = "https://maps.googleapis.com";
    public DataLoader()
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                                        .setEndpoint(APIUrl).build();

        WebServiceCall webServiceCall = restAdapter.create(WebServiceCall.class);
        //webServiceCall.GetWeather();
    }
}
