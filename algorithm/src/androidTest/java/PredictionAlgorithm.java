import android.app.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Fonz Yáñez on 12/10/2015.
 */

public class PredictionAlgorithm {

    private Museum museum;
    private Monument monument;

    //Interface or class?
    private TourismUnit tourismUnit;

    Notification notification = new Notification();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String predAlg(){
        /* Conditions for the algorithm to accomplish

        · Last notification sent less than 2min ago
        · Know the current weather, right NOW, where the user is
         */
        String jsonOutput = gson.toJson(tourismUnit);
        Long lastNotifi = notification.when() > 20000;

        // User is not an entity itself, just a temporal object till we find out how to retrieve data
        Weather currentWeather = user.getLocation().getCurrentTime().getWeather();

    if(lastNotifi) {

        switch (currentWeather){

            case (isSunny):
            tourismUnit.isMonument().showNotification();

            case (isRainy && user.getCurrentTime.inRange(museum.getOpeningHours())):
            tuorismUnit.isMuseum().showNotification();
                  /* Consider what will happen if the user is close to a Museum on a rainy day
                but is closed.

                Sent a notification to recommend the user to come back tomorrow

                What about if today is rainy and tomorrow sunny?
                Would you recommend the user to come back to that Museum?
                 */
            break;
    }
    }else {
        //Print error || Do nothing
    }
        //Retrun a JSON string with all the attributes of TourismUnit
        return jsonOutput;
    }
}
