package hr.evolaris.air.foi.evolaris_smarttourism;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import hr.evolaris.air.foi.evolaris_smarttourism.db.MessageActions;

public class NotificationListenerService
        extends WearableListenerService
{
    public NotificationListenerService()
    {
        super();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent)
    {

        if(messageEvent.getPath().equalsIgnoreCase(MessageActions.START_ACTIVITY.text))
        {
            Intent intent = new Intent(this, WearableListViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (messageEvent.getPath().equalsIgnoreCase(MessageActions.RECEIVE_RECOMMENDATION.text))
        {
            Intent intent = new Intent(this, RecommendationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            super.onMessageReceived(messageEvent);
        }

    }

}