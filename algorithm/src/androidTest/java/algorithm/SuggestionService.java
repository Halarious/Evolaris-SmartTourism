package algorithm;

import android.os.Message;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_time.TimeIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.db.UserLocationManager;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceDataLoader;

public class SuggestionService {

    private WeatherIntermediaryResult wir;
    private LocationIntermediaryResult lir;
    private TimeIntermediaryResult tir;
    private DistanceDataLoader ddl;


    // Weather id from 500 to 531 --> Rain, all types
    public Message suggestionService(WeatherIntermediaryResult wir, LocationIntermediaryResult lir, TimeIntermediaryResult tir) {
        //If it rains (all types)
        if (WeatherIntermediaryResult.weather.id < 531 && WeatherIntermediaryResult.weather.id > 500) {

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


            /* Find how two locations are compared
            * should be predefined formula/algorithm
            *
            * To test, predefine weather, mock location
            *
            * Define several messages to be displayed, i.e. Collection<Message>
            *
            *
            * Merge both branches before coming to the defense
            * */

            if (userCurrentLatLng.equals(destinationsLatLngArray)){
                Message m = new Message();
                m.getData();
                return m;
            }
        }


        // Weather id=800 --> Clear sky
        else if (WeatherIntermediaryResult.weather.id.equals(800)) {

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


            if (userCurrentLatLng.equals(destinationsLatLngArray)) {
                Message m = new Message();
                m.getData();
                return m;
            }

        }
        return new Message();
    }


}
