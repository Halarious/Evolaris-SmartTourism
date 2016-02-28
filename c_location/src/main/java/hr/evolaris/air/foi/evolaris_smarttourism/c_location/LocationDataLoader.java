package hr.evolaris.air.foi.evolaris_smarttourism.c_location;


import hr.evolaris.air.foi.evolaris_smarttourism.core.Latches;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LocationDataLoader
{
    private String APIKey = "AIzaSyAPGo4La9l47-J2kc180bpAGevLu7d-4Sk";
    private String APIUrl = "https://maps.googleapis.com";

    private WebServiceCall webServiceCall;

    public LocationDataLoader()
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
            LocationIntermediaryResult.response = response;
            if(locationList.results.size() > 0)
            {
                LocationIntermediaryResult.locationList = locationList;

            }
            else
            {

            }
            Latches.getLatch().fetchCountDownLatch.countDown();
        }

        @Override
        public void failure(RetrofitError error)
        {
            error.printStackTrace();
            Latches.getLatch().fetchCountDownLatch.countDown();
        }
    };

    public void getRestaurant(String latitude, String longitude)
    {

        webServiceCall.getLocations(LocationContextConstants.SEARCH_TYPE_NEARBY, LocationContextConstants.FORMAT_JSON,
                                    latitude + "," + longitude,
                                    1000, "restaurant",
                                    APIKey, BaseCallback);
    }
}
