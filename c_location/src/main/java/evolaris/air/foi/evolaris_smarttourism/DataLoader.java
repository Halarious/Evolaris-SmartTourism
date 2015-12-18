package evolaris.air.foi.evolaris_smarttourism;

import android.view.View;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DataLoader
{
    private String APIKey = "AIzaSyAPGo4La9l47-J2kc180bpAGevLu7d-4Sk";
    private String APIUrl = "https://maps.googleapis.com";

    private TextView View;
    private WebServiceCall webServiceCall;

    public DataLoader()
    {
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                                        .setEndpoint(APIUrl).build();

        webServiceCall = restAdapter.create(WebServiceCall.class);
    }


    private Callback<LocationList> BaseCallback = new Callback<LocationList>()
    {

        @Override
        public void success(LocationList locationList, Response response)
        {
            Location L = locationList.results.get(0);
            String pID = L.place_id;

            View.setText(pID);
        }

        @Override
        public void failure(RetrofitError error)
        {
            View.setText("Failed!");
        }
    };

    public void GetMuseums(TextView View)
    {
        this.View = View;
        webServiceCall.getLocations("radarsearch", "json", "46.3053777", "16.3328265", 5000, "museum",
                                    APIKey, BaseCallback);
    }
}
