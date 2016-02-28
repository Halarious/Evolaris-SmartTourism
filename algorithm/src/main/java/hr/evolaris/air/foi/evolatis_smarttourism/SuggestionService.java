package hr.evolaris.air.foi.evolatis_smarttourism;


import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_time.TimeIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.core.Latches;
import hr.evolaris.air.foi.evolaris_smarttourism.db.NotificationMessage;
import hr.evolaris.air.foi.evolaris_smarttourism.db.UserLocationManager;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service.DistanceIntermediaryResult;

public class SuggestionService extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {
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

        try {
            Latches.getLatch().DistanceMatrixCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int minimumDistance = 501;
        int minimumDistanceIndexMonument = -1;
        int minimumDistanceIndexMuseumRestaurant = -1;
        boolean isOpen = false;

        for (int i = 0; i < DistanceIntermediaryResult.distanceClass.rows.get(0).elements.size(); i++)
        {

            if (LocationIntermediaryResult.locationList.results.get(i).openingHours == null ||
                    LocationIntermediaryResult.locationList.results.get(i).openingHours.open_now == true)
            {

                isOpen = true;
                int distanceMeters = DistanceIntermediaryResult.distanceClass.rows.get(0).elements.get(i).distance.value;

                if (distanceMeters <= 500) {
                    if (distanceMeters < minimumDistance) {
                        minimumDistance = distanceMeters;

                        if (LocationIntermediaryResult.locationList.results.get(i).types.get(0).equals("museum") ||
                                LocationIntermediaryResult.locationList.results.get(i).types.get(0).equals("restaurant"))
                        {

                            minimumDistanceIndexMuseumRestaurant = i;

                        }
                        else if (LocationIntermediaryResult.locationList.results.get(i).types.get(0).equals("church") ||
                                LocationIntermediaryResult.locationList.results.get(i).types.get(0).equals("mosque") ||
                                LocationIntermediaryResult.locationList.results.get(i).types.get(0).equals("park") ||
                                LocationIntermediaryResult.locationList.results.get(i).types.get(0).equals("zoo"))
                        {
                            minimumDistanceIndexMonument = i;
                        }

                    }
                }
            }
            else {

            }

        }

        // Weather id from 500 to 531 --> Rain, all types
        //If it rains (all types)
        if (WeatherIntermediaryResult.weather.weather.get(0).id <= 531 && WeatherIntermediaryResult.weather.weather.get(0).id >= 500 ||
                WeatherIntermediaryResult.weather.weather.get(0).id <= 232 && WeatherIntermediaryResult.weather.weather.get(0).id >= 200 ||
                WeatherIntermediaryResult.weather.weather.get(0).id <= 321 && WeatherIntermediaryResult.weather.weather.get(0).id >= 300)
        {

            if (minimumDistanceIndexMuseumRestaurant == -1) {
                NotificationMessage.title = "It is rainy so hard!";
                NotificationMessage.message = "And looks like there is nothing close to you, go home pal";
            }

            else if (isOpen == true) {
                NotificationMessage.title = "It is a rainy day :(";
                NotificationMessage.message = "But don't let it stop you, you're really close to "
                        + LocationIntermediaryResult.locationList.results.get(minimumDistanceIndexMuseumRestaurant).name;
            }
            else {
                NotificationMessage.title = "Looks like everything around you is closed today...";
                NotificationMessage.message = "Why don't you try again tomorrow?";
            }
        }
        // Weather id=800 --> Clear sky
        else if (WeatherIntermediaryResult.weather.weather.get(0).id.equals(800) ||
                WeatherIntermediaryResult.weather.weather.get(0).id <= 804 && WeatherIntermediaryResult.weather.weather.get(0).id >= 801)
        {

            if (minimumDistanceIndexMonument == -1) {
                NotificationMessage.title = "Nice weather uh?";
                NotificationMessage.message = "But there is nothing close to you right now, why don't you go on a trip?";
            }

            else if (TimeIntermediaryResult.timePoint.hour >= 6 && TimeIntermediaryResult.timePoint.hour < 12) {
                NotificationMessage.title = "Good morning! Today the sky is pretty clear, right?";
                NotificationMessage.message = "And you're close to " +
                        LocationIntermediaryResult.locationList.results.get(minimumDistanceIndexMonument).name
                        + ",let's go and visit it";

            }
            else if (TimeIntermediaryResult.timePoint.hour >= 12 && TimeIntermediaryResult.timePoint.hour < 16) {
                NotificationMessage.title = "Good afternoon, how was your morning?";
                NotificationMessage.message = "Just wanted to tell you that you're close to " +
                        LocationIntermediaryResult.locationList.results.get(minimumDistanceIndexMonument).name
                        + ",let's go and visit it";
            }
            else if (TimeIntermediaryResult.timePoint.hour >= 16 && TimeIntermediaryResult.timePoint.hour < 21) {
                NotificationMessage.title = "Evening! How is your day going?";
                NotificationMessage.message = "Hope you're not getting bored because you have something to see close to you, the " +
                        LocationIntermediaryResult.locationList.results.get(minimumDistanceIndexMonument).name
                        + ",let's go and visit it";
            }
            else if (TimeIntermediaryResult.timePoint.hour >= 21 ||
                    (TimeIntermediaryResult.timePoint.hour >= 0 && TimeIntermediaryResult.timePoint.hour <= 5))
            {
                NotificationMessage.title = "Night has come, time not to stop!";
                NotificationMessage.message = "There is something that you should really go and see it, it is called " +
                        LocationIntermediaryResult.locationList.results.get(minimumDistanceIndexMonument).name
                        + ",let's go and visit it";
            }
        }
            return null;
            //return new Message();
    }

        @Override
        protected void onPostExecute (Object o)
        {
            super.onPostExecute(o);
        }

}
