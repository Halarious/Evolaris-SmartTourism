package evolaris.air.foi.evolaris_smarttourism;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebServiceCall
{

    @FormUrlEncoded
    @GET("&lat={latitude}&lon={longitude}&appid={key}")
    void GetWeather(@Path("latitude") int latitude, @Path("longitude") int longitude,
                    @Path("key") String apiKey, Callback<Weather> response);
}
