package evolaris.air.foi.evolaris_smarttourism.c_location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class JsonAdapter
{
    public static ArrayList<Object> getLocations(String jsonString)
    {
        ArrayList<Object> locations = new ArrayList<Object>();

        try
        {
            JSONArray jsonArray = new JSONArray(jsonString);
            int size = jsonArray.length();

            for(int i = 0; i<size; i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

            }
        }
        catch (JSONException exception)
        {
            exception.printStackTrace();
        }
        return locations;
    }
}