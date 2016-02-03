package hr.evolaris.air.foi.evolaris_smarttourism.db;

public enum MessageActions
{
    START_ACTIVITY("/start_activity"),
    RECEIVE_RECOMMENDATION("/receive_recommendation");

    public final String text;

     private MessageActions(final String text)
    {
        this.text = text;
    }
}
