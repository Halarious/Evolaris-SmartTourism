package hr.evolaris.air.foi.evolaris_smarttourism.db;

public enum MessageActions
{
    START_ACTIVITY("/start_activity"),
    TEST_STRING("/test_string");

    public final String text;

    private MessageActions(final String text)
    {
        this.text = text;
    }
}
