package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class FetchAddressIntentService extends IntentService
{
    private ResultReceiver resultReceiver;

    public FetchAddressIntentService()
    {
        super("FetchAddressIntentService");
    }
    public FetchAddressIntentService(String name)
    {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String errorMessage = "";

        Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
        resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        List<Address> addresses = null;

        try
        {
            addresses = geocoder.getFromLocation(location.getLatitude(),
                                                 location.getLongitude(),
                                                 1);
        }catch(IOException exception)
        {


        }catch(IllegalArgumentException exception)
        {

        }

        if(addresses == null || addresses.size() == 0)
        {
            if(errorMessage.isEmpty())
            {
                errorMessage = getString(R.string.address_not_found);
                Log.e("", errorMessage);
            }
            deliverResultToReceiver(intent, Constants.FAILURE_RESULT, errorMessage);
        }
        else
        {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();
            for(int i = 0; i < address.getMaxAddressLineIndex(); i++)
            {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i("", getString(R.string.address_found));
            deliverResultToReceiver(intent,
                                    Constants.SUCCESS_RESULT,
                                    TextUtils.join(System.getProperty("line.separator"),
                                                    addressFragments));
        }
    }

    private void deliverResultToReceiver(Intent intent, int resultCode, String message)
    {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, message);

        resultReceiver.send(resultCode, bundle);
    }
}
