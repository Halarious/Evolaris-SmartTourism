package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

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
        }

        @Override
        public void failure(RetrofitError error)
        {
            error.printStackTrace();
        }
    };

    public void getDistanceMatrix(ArrayList<LatLng> origins, ArrayList<LatLng> destinations)
    {
        String originLatitude = String.valueOf(origins.get(0).latitude);
        String originLongitude = String.valueOf(origins.get(0).longitude);

        String destinationLatitude = String.valueOf(destinations.get(0).latitude);
        String destinationLongitude = String.valueOf(destinations.get(0).longitude);

        /*
        for (int i = 0; i < destinations.size(); i++)
        {

            destinationsLatLng = String.valueOf((destinations.get(i).latitude + "," + destinations.get(i).longitude));

            if (destinationsLatLngIncrement.equals(destinations.size()-1))
            {
                destinationsLatLngIncrement = String.valueOf((destinations.get(i++).latitude + "," + destinations.get(i++).longitude));

                finalLatLng = destinationsLatLng + "|" + destinationsLatLngIncrement;

            }else
            {
                finalLatLng = destinationsLatLng;
            }
        } */

        webServiceCall.getDistanceMatrix("json",
                                        originLatitude + "," + originLongitude,
                                        destinationLatitude + "," + destinationLongitude,
                                        "walking",
                                        APIKey, BaseCallback);
    }
}
