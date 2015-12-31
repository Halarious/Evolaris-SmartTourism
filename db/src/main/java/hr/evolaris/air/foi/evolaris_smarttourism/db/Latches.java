package hr.evolaris.air.foi.evolaris_smarttourism.db;

import java.util.concurrent.CountDownLatch;

public class Latches
{
    private static Latches instance;
    public CountDownLatch countDownLatch;

    private Latches()
    {
        countDownLatch = null;
    }

    public CountDownLatch setCountDownLatch(int count)
    {
        countDownLatch = new CountDownLatch(count);
        return countDownLatch;
    }
    public static Latches getLatch()
    {
        if(instance == null)
        {
            instance = new Latches();
        }
        return instance;
    }
}
