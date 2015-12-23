package hr.evolaris.air.foi.evolaris_smarttourism;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import hr.evolaris.air.foi.evolaris_smarttourism.c_location.LocationDataLoader;
import hr.evolaris.air.foi.evolaris_smarttourism.db.MessageActions;

public class        MainActivity
        extends     AppCompatActivity

        implements  GoogleApiClient.OnConnectionFailedListener,
                    GoogleApiClient.ConnectionCallbacks
{

    int testNotificationID = 001;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String mLatitudeText;
    private String mLongitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGoogleApiClient();

        String googleAPIKey = getResources().getString(R.string.google_API_Key);
        String placeID = "ChIJlR89EtaqaEcR75ls5fh12cs";
        PlacesAPI_getName(placeID);

        final LocationDataLoader dataLoader = new LocationDataLoader();

        Button clicky = (Button)findViewById(R.id.clicky);
        clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendMessage(MessageActions.START_ACTIVITY.text, "");
                dataLoader.getMuseums((TextView) findViewById(R.id.MyTextView));
            }
        });

        //dataLoader.getWeather((TextView)findViewById(R.id.MyTextView));

        /*
        Intent viewIntent = new Intent(this, ViewEventActivity.class);
        //viewIntent.putExtra(EXTRA_EVENT_ID, eventID);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action toastAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_setting_light,
                        getString(R.string.toastLabel), viewPendingIntent).build();

        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode("Zagreb"));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action mapAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_plusone_medium_off_client,
                        getString(R.string.mapLabel), mapPendingIntent).build();


        android.support.v4.app.NotificationCompat.BigTextStyle bigStyle =
                new NotificationCompat.BigTextStyle();
        bigStyle.bigText(getString(R.string.eventText));

        NotificationCompat.Builder notificationBuilder =
                (NotificationCompat.Builder) new                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_event)
                        .setContentTitle(getResources().getString(R.string.eventTitle))
                        .setContentText(getResources().getString(R.string.eventText))
                        .setContentIntent(viewPendingIntent)
                        .extend(new android.support.v4.app.NotificationCompat.WearableExtender()
                                    .addAction(mapAction)
                                    .addAction(toastAction))
                        .setStyle(bigStyle);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(testNotificationID, notificationBuilder.build());*/
    }

    @Override
    protected void onStop()
    {

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                         mGoogleApiClient);
        if (mLastLocation != null) {
            String Accuracy = (String.valueOf(mLastLocation.getAccuracy()));
            String Time = (String.valueOf(mLastLocation.getTime()));
            mLatitudeText = (String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText = (String.valueOf(mLastLocation.getLongitude()));
        }
    }


    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        if(connectionResult.getErrorCode() == ConnectionResult.API_UNAVAILABLE)
        {
            Log.i("", "API unavailable!");
        }
    }

    private void initializeGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(LocationServices.API)
                .addApiIfAvailable(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if(mGoogleApiClient != null)
        {
            mGoogleApiClient.connect();
        }
    }

    private void sendMessage( final String path, final String text )
    {
        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mGoogleApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mGoogleApiClient, node.getId(), path, text.getBytes() ).await();
                }
            }
        }).start();
    }

    public void PlacesAPI_getName(String placeID)
    {
        Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeID)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0)
                        {
                            Log.i("", "Place found: " + places.get(0).getName());
                        }
                        else
                        {
                            Log.e("", "Place not found");
                        }
                        places.release();
                    }
                });

    }
}