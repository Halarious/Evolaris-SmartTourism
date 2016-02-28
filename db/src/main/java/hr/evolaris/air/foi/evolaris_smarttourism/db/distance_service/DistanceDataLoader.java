package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hr.evolaris.air.foi.evolaris_smarttourism.core.Latches;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DistanceDataLoader
{
    private String APIKey = "AIzaSyAPGo4La9l47-J2kc180bpAGevLu7d-4Sk";
    private String APIUrl = "https://maps.googleapis.com";

    private DistanceWebServiceCall webServiceCall;

    public DistanceDataLoader()
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(APIUrl).build();

        webServiceCall = restAdapter.create(DistanceWebServiceCall.class);
    }

    private Callback<DistanceClass> BaseCallback = new Callback<DistanceClass>()
    {

        @Override
        public void success(DistanceClass distanceClass, Response response)
        {
            DistanceIntermediaryResult.response = response;
            if(distanceClass.rows.size() > 0)
            {
                DistanceIntermediaryResult.distanceClass = distanceClass;
            }
            else
            {

            }

            Latches.getLatch().DistanceMatrixCountDownLatch.countDown();
        }

        @Override
        public void failure(RetrofitError error)
        {
            error.printStackTrace();
            Latches.getLatch().DistanceMatrixCountDownLatch.countDown();
        }
    };

    public void getDistanceMatrix(ArrayList<LatLng> origins, ArrayList<LatLng> destinations)
    {
        String originLatitude = String.valueOf(origins.get(0).latitude);
        String originLongitude = String.valueOf(origins.get(0).longitude);

        String destinationsLatLng;
        String finalDestinationsLatLng = null;
        for (int i = 0; i < destinations.size(); i++)
        {
            destinationsLatLng = String.valueOf((destinations.get(i).latitude + "," + destinations.get(i).longitude));

            if(i != 0)
            {
                finalDestinationsLatLng = finalDestinationsLatLng + "|" + destinationsLatLng;
            }
            else
            {
                finalDestinationsLatLng = destinationsLatLng;
            }
        }

        webServiceCall.getDistanceMatrix("json",
                                        originLatitude + "," + originLongitude,
                                        finalDestinationsLatLng,
                                        "walking",
                                        APIKey, BaseCallback);
    }
}
