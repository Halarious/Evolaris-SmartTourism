package evolaris.air.foi.evolaris_smarttourism;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebServiceCall
{

    @FormUrlEncoded
    @GET("/data/2.5/weather?&lat={latitude}&lon={longitude}&appid={key}")
    void getWeather(@Path("latitude") String latitude, @Path("longitude") String longitude,
                    @Path("key") String apiKey, Callback<Weather> response);
}
