import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fonz Yáñez on 12/10/2015.
 */
public class JsonAdapter {

    public static ArrayList<TourismUnit> getTourismUnits(String jsonString){

        ArrayList<TourismUnit> tourismUnits = new ArrayList<TourismUnit>();
        try{
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for(int i = 0; i < size; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TourismUnit tourismUnit = new TourismUnit(
                        //jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getString("description")
                );
                tourismUnits.add(tourismUnit);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return tourismUnits;
    }
}
