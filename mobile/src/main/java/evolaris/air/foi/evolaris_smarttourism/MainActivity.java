package evolaris.air.foi.evolaris_smarttourism;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

public class       MainActivity
        extends     AppCompatActivity

        implements  GoogleApiClient.OnConnectionFailedListener,
                    GoogleApiClient.ConnectionCallbacks
{

    int testNotificationID = 001;
    private static final String START_ACTIVITY = "/start_activity";
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGoogleApiClient();
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
    protected void onDestroy() {
        super.onDestroy();

        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        sendMessage(START_ACTIVITY, "");
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

        }
    }

    private void initializeGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //.addApi(PLACES_API_GOES_HERE)
                //.addScope(I_DO_NOT_EVEN_KNOW)
                .addApiIfAvailable(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    private void sendMessage( final String path, final String text ) {
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
}