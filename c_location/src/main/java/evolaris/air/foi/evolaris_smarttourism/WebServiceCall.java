package evolaris.air.foi.evolaris_smarttourism;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WebServiceCall
{
    @FormUrlEncoded
    @GET("/maps/api/place/{searchType}/{outputFormat}?location={lat},{lon}&radius={radius}&types={locationTypes}&key={apiKey}")
    void GetWeather(@Path("searchType") String searchType, @Path("outputFormat") String outputFormat,
                    @Path("lat") int latitude, @Path("lon") int longitude, @Path("radius") int radius,
                    @Path("locationTypes") String locationTypes,@Path("apiKey") String apiKey,
                    Callback<LocationList> response);
}