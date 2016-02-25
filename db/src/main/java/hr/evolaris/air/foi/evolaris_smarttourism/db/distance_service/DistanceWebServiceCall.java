package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface DistanceWebServiceCall
{
    @GET("/maps/api/distancematrix/{outputFormat}")
    void getDistanceMatrix( @Path("outputFormat") String outputFormat,
                            @Query("origins") String originLatitudeLongitude,
                            @Query("destinations") String destinationLatitudeLongitude,
                            @Query("mode") String mode, @Query("key") String apiKey,
                            Callback<DistanceClass> response);
}
