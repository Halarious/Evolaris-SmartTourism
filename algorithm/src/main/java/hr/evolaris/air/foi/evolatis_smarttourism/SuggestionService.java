package hr.evolaris.air.foi.evolatis_smarttourism;


import android.os.AsyncTask;
import android.os.Message;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.core.Latches;
import hr.evolaris.air.foi.evolaris_smarttourism.db.NotificationMessage;
import hr.evolaris.air.foi.evolaris_smarttourism.db.UserLocationManager;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceIntermediaryResult;

public class SuggestionService extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] params)
    {
        DistanceDataLoader ddl = new DistanceDataLoader();

        //Retrieve user current location, getting it in LatLng parameters
        LatLng userCurrentLatLng = new LatLng(UserLocationManager.getInstance().currentLocation.getLatitude(),
                UserLocationManager.getInstance().currentLocation.getLongitude());

        ArrayList<LatLng> userCurrentLatLngArray;
        userCurrentLatLngArray = new ArrayList<LatLng>();
        userCurrentLatLngArray.add(userCurrentLatLng);


        //Retrieve all items location, getting it in LatLng parameters
        ArrayList<LatLng> destinationsLatLngArray = new ArrayList<LatLng>();

        for (int i = 0; i < LocationIntermediaryResult.locationList.results.size(); i++) {

            LatLng destinationLatLng = new LatLng(LocationIntermediaryResult.locationList.results.get(i).geometry.location.lat,
                    LocationIntermediaryResult.locationList.results.get(i).geometry.location.lng);

            destinationsLatLngArray.add(destinationLatLng);
        }

        Latches.getLatch().setDistanceMatrixCountDownLatch(1);
        ddl.getDistanceMatrix(userCurrentLatLngArray, destinationsLatLngArray);

        try
        {
            Latches.getLatch().DistanceMatrixCountDownLatch.await();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        int minimumDistance = 501;
        int minimumDistanceIndex = 0;
        for(int i =0; i < DistanceIntermediaryResult.distanceClass.rows.get(0).elements.size(); i++)
        {
            int distanceMeters = DistanceIntermediaryResult.distanceClass.rows.get(0).elements.get(i).distance.value;
            if (distanceMeters <= 500)
            {
                if(distanceMeters < minimumDistance)
                {
                    minimumDistance = distanceMeters;
                    minimumDistanceIndex = i;
                }
            }
        }

        // Weather id from 500 to 531 --> Rain, all types
        //If it rains (all types)
        if (WeatherIntermediaryResult.weather.weather.get(0).id <= 531 && WeatherIntermediaryResult.weather.weather.get(0).id >= 500 ||
                WeatherIntermediaryResult.weather.weather.get(0).id <= 232 && WeatherIntermediaryResult.weather.weather.get(0).id >= 200 ||
                WeatherIntermediaryResult.weather.weather.get(0).id <= 321 && WeatherIntermediaryResult.weather.weather.get(0).id >= 300)
        {

            NotificationMessage.title = "It is a rainy day :(";
            NotificationMessage.message = "But don't let it stop you, you're really close to "
                    + LocationIntermediaryResult.locationList.results.get(minimumDistanceIndex).name;

            /*
            * To test, predefine weather, mock location
            *
            * Define several messages to be displayed, i.e. Collection<Message>
            *
            * */
        }
        // Weather id=800 --> Clear sky
        else if (WeatherIntermediaryResult.weather.weather.get(0).id.equals(800) ||
                WeatherIntermediaryResult.weather.weather.get(0).id <= 804 && WeatherIntermediaryResult.weather.weather.get(0).id >= 801)
        {


            NotificationMessage.title = "Today the sky is pretty clear, right?";
            NotificationMessage.message = "And you're close to " +
                    LocationIntermediaryResult.locationList.results.get(minimumDistanceIndex).name
                    + ",let's go and visit it";


        }

        return null;
        //return new Message();
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
    }
}
