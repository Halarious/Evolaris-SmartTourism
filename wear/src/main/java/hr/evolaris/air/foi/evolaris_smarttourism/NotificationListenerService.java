package hr.evolaris.air.foi.evolaris_smarttourism;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import hr.evolaris.air.foi.evolaris_smarttourism.db.MessageActions;
import hr.evolaris.air.foi.evolaris_smarttourism.db.NotificationMessage;

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
        String stringData;
        String[] stringTitleMessage;

        stringData = ASCIItoCharacter(messageEvent.getData());
        stringTitleMessage = stringData.split("-");
        NotificationMessage.title = stringTitleMessage[0];
        NotificationMessage.message = stringTitleMessage[1];

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

    public String ASCIItoCharacter(byte[] characterMap)
    {
        String outputString = null;
        for(int i = 0; i < characterMap.length; i++)
        {
            if(i==0)
            {
                outputString = Character.toString((char)characterMap[i]);
            }
            else
            {
                outputString = outputString + Character.toString((char)characterMap[i]);
            }
        }

        return outputString;
    }

}