package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import retrofit.RestAdapter;

public class DistanceDataLoader {
    private String APIKey = "AIzaSyAPGo4La9l47-J2kc180bpAGevLu7d-4Sk";
    private String APIUrl = "https://maps.googleapis.com";

    private DistanceWebServiceCall webServiceCall;

    public DistanceDataLoader() {
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(APIUrl).build();

        webServiceCall = restAdapter.create(DistanceWebServiceCall.class);
    }

    public void getDistanceMatrix(ArrayList<LatLng> origins, ArrayList<LatLng> destinations) {

        String originsLatitude = String.valueOf(origins.get(0).latitude);
        String originsLongitude = String.valueOf(origins.get(0).longitude);

        String destinationsLatLng = "";
        String destinationsLatLngIncrement = "";
        String finalLatLng = "";

        for (int i = 0; i < destinations.size(); i++) {

            destinationsLatLng = String.valueOf((destinations.get(i).latitude + "," + destinations.get(i).longitude));

            if (destinationsLatLngIncrement.equals(destinations.size()-1)){
                destinationsLatLngIncrement = String.valueOf((destinations.get(i++).latitude + "," + destinations.get(i++).longitude));

                finalLatLng = destinationsLatLng + "|" + destinationsLatLngIncrement;

            }else{
                finalLatLng = destinationsLatLng;
            }
        }


        webServiceCall.getDistanceMatrix("json",
                originsLatitude + "," + originsLongitude,
                finalLatLng,
                "walking",
                APIKey);


    }
}