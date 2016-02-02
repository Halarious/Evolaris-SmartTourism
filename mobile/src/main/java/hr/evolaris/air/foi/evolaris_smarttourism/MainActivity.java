package hr.evolaris.air.foi.evolaris_smarttourism;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
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

import java.text.DateFormat;
import java.util.Date;

import hr.evolaris.air.foi.evolaris_smarttourism.c_weather.WeatherIntermediaryResult;
import hr.evolaris.air.foi.evolaris_smarttourism.db.MessageActions;

public class        MainActivity
        extends     AppCompatActivity

        implements  GoogleApiClient.OnConnectionFailedListener,
                    GoogleApiClient.ConnectionCallbacks
{
    private PopupWindow popupWindow;

    private GoogleApiClient mGoogleApiClient;
    private UserLocationManager userLocationInstance;
    private AddressResultReceiver addressResultReceiver;

    private DrawerLayout mDrawer;
    private Toolbar mDrawerToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGoogleApiClient();

        userLocationInstance = UserLocationManager.getInstance();
        addressResultReceiver = new AddressResultReceiver(new Handler());

        popupWindow = new PopupWindow(this);
        final View popupView = this.getLayoutInflater().inflate(R.layout.popup_window, null);
        popupWindow.setContentView(popupView.findViewById(R.id.popup_view));
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.AnimationPopup);

        String placeID = "ChIJlR89EtaqaEcR75ls5fh12cs";
        PlacesAPI_getName(mGoogleApiClient, placeID);

        TestHandle.context = this;
        Button clicky = (Button)findViewById(R.id.clicky);
        clicky.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                ((TextView) findViewById(R.id.MyTextView)).setText(
                        "");
                sendMessage(MessageActions.START_ACTIVITY.text, "");
                TestHandle.progressDialog = TestHandle.progressDialog.show(TestHandle.context, "Processing", "Please wait", true);
                new AsyncCollectInfo().execute();
            }
        });

        Button clicky2 = (Button)findViewById(R.id.clicky2);
        clicky2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ((TextView) findViewById(R.id.MyTextView2)).setText(
                        userLocationInstance.lastUpdateTime);

                if(!popupWindow.isShowing())
                {
                popupWindow.showAtLocation(popupView.findViewById(R.id.popup_view), Gravity.BOTTOM, 10, 10);
                popupWindow.update(50, 50, 300, 80);
                }
                else
                {
                    popupWindow.dismiss();
                }
            }
        });

        mDrawerToolbar = (Toolbar) findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mDrawerToolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = CreateActionBarToggle();
        mDrawer.setDrawerListener(mDrawerToggle);

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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemID = item.getItemId();

        switch(itemID)
        {
            default:
                break;
        }

        return true;
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        Location LastLocation;
        LastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (LastLocation != null)
        {
            userLocationInstance.currentLocation = LastLocation;
            userLocationInstance.lastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        }

        userLocationInstance.startLocationUpdated(mGoogleApiClient);
        startGeocodeIntentService(userLocationInstance.currentLocation);
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
                for(Node node : nodes.getNodes())
                {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mGoogleApiClient, node.getId(), path, text.getBytes() ).await();
                }
            }
        }).start();
    }

    private void startGeocodeIntentService(Location passedLocation)
    {
        Location location = passedLocation;
        if(location != null)
        {
            Intent intent = new Intent(this, FetchAddressIntentService.class);
            intent.putExtra(Constants.RECEIVER, addressResultReceiver);
            intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
            startService(intent);
        }
    }

    private ActionBarDrawerToggle CreateActionBarToggle()
    {
        return new ActionBarDrawerToggle(this, mDrawer, mDrawerToolbar, R.string.drawer_open , R.string.drawer_close)
        {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    public void PlacesAPI_getName(GoogleApiClient googleApiClient, String placeID)
    {
        Places.GeoDataApi.getPlaceById(googleApiClient, placeID)
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


    class AddressResultReceiver extends ResultReceiver
    {
        public String resultAddress;

        public AddressResultReceiver(Handler handler)
        {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData)
        {
            if (resultCode == Constants.SUCCESS_RESULT)
            {
                resultAddress = resultData.getString(Constants.RESULT_DATA_KEY);
                ((TextView)findViewById(R.id.MyTextView2)).setText(resultAddress);
            }
            else
            if (resultCode == Constants.FAILURE_RESULT)
            {
                //TODO(rob): logging
            }

        }
    }
}