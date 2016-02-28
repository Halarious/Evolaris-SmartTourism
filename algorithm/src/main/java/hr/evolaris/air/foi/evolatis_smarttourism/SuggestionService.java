package hr.evolaris.air.foi.evolatis_smarttourism;


import android.os.Message;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_time.TimeIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.db.NotificationMessage;
import hr.evolaris.air.foi.evolaris_smarttourism.db.UserLocationManager;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceIntermediaryResult;

public class SuggestionService {

    private WeatherIntermediaryResult wir;
    private LocationIntermediaryResult lir;
    private TimeIntermediaryResult tir;
    private DistanceDataLoader ddl;


    // Weather id from 500 to 531 --> Rain, all types
    public Message suggestionService() {

        //If it rains (all types)
        if (WeatherIntermediaryResult.weather.weather.get(0).id < 531 && WeatherIntermediaryResult.weather.weather.get(0).id > 500) {

            //Retrieve user current location, getting it in LatLng parameters
            LatLng userCurrentLatLng = new LatLng(UserLocationManager.getInstance().currentLocation.getLatitude(),
                    UserLocationManager.getInstance().currentLocation.getLongitude());

            ArrayList<LatLng> userCurrentLatLngArray;
            userCurrentLatLngArray = new ArrayList<LatLng>();
            userCurrentLatLngArray.add(userCurrentLatLng);


            //Retrieve all items location, getting it in LatLng parameters
            ArrayList<LatLng> destinationsLatLngArray = new ArrayList<LatLng>();

            for (int i = 0; i < LocationIntermediaryResult.locationList.results.size(); i++) {

                LatLng destinationLatLng = new LatLng(LocationIntermediaryResult.locationList.results.get(i).geometry.location.getLatitude(),
                        LocationIntermediaryResult.locationList.results.get(i).geometry.location.getLongitude());

                destinationsLatLngArray.add(destinationLatLng);
            }


            ddl.getDistanceMatrix(userCurrentLatLngArray, destinationsLatLngArray);

            if (DistanceIntermediaryResult.distanceClass.rows.get(0).elements.get(0).distance.value < 200) {
                NotificationMessage.title = "Today the sky is pretty clear, right?";
                NotificationMessage.message = "And you're close to " + LocationIntermediaryResult.locationList.results.get(0).name + ",let's go and visit it";
            }

            /*
            * To test, predefine weather, mock location
            *
            * Define several messages to be displayed, i.e. Collection<Message>
            *
            * */
        }


        // Weather id=800 --> Clear sky
        else if (WeatherIntermediaryResult.weather.weather.get(0).id.equals(800)) {

            //Retrieve user current location, getting it in LatLng parameters
            LatLng userCurrentLatLng = new LatLng(UserLocationManager.getInstance().currentLocation.getLatitude(),
                    UserLocationManager.getInstance().currentLocation.getLongitude());

            ArrayList<LatLng> userCurrentLatLngArray;
            userCurrentLatLngArray = new ArrayList<LatLng>();
            userCurrentLatLngArray.add(userCurrentLatLng);


            //Retrieve all items location, getting it in LatLng parameters
            ArrayList<LatLng> destinationsLatLngArray = new ArrayList<LatLng>();

            for (int i = 0; i < LocationIntermediaryResult.locationList.results.size(); i++) {

                LatLng destinationLatLng = new LatLng(LocationIntermediaryResult.locationList.results.get(i).geometry.location.getLatitude(),
                        LocationIntermediaryResult.locationList.results.get(i).geometry.location.getLongitude());

                destinationsLatLngArray.add(destinationLatLng);
            }

            ddl.getDistanceMatrix(userCurrentLatLngArray, destinationsLatLngArray);


            if (DistanceIntermediaryResult.distanceClass.rows.get(0).elements.get(0).distance.value < 200) {
                NotificationMessage.title = "It is a rainy day :(";
                NotificationMessage.message = "But don't let it stop you, you're really close to " + LocationIntermediaryResult.locationList.results.get(0).name;
            }

        }
        return new Message();
    }
}
