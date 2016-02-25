package hr.evolaris.air.foi.evolaris_smarttourism.c_location;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface WebServiceCall
{

    @GET("/maps/api/place/{searchType}/{outputFormat}")//?location={lat},{lon}&radius={radius}&types={locationTypes}&key={apiKey}")
    void getLocations(  @Path("searchType") String searchType, @Path("outputFormat") String outputFormat,
                        @Query("location") String latitudeLongitude, @Query("radius") int radius,
                        @Query("types") String locationTypes, @Query("key") String apiKey,
                        Callback<LocationList> response);
}