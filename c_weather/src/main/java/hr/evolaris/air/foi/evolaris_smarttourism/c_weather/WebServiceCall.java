package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WebServiceCall
{

    @GET("/data/2.5/weather")
    void getWeather(@Query("lat") String latitude, @Query("lon") String longitude,
                    @Query("appid") String apiKey, Callback<Weather> response);
}
