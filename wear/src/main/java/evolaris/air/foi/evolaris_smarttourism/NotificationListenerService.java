package evolaris.air.foi.evolaris_smarttourism;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class NotificationListenerService
        extends WearableListenerService
{
    private static final String START_ACTIVITY = "/start_activity";

    public NotificationListenerService()
    {
        super();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent)
    {

        if(messageEvent.getPath().equalsIgnoreCase(START_ACTIVITY))
        {
            Intent intent = new Intent(this, WearableListViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            super.onMessageReceived(messageEvent);
        }

    }

}
