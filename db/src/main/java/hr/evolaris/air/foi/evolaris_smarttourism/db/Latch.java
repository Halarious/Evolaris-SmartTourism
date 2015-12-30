package hr.evolaris.air.foi.evolaris_smarttourism.db;

import java.util.concurrent.CountDownLatch;

public class Latch
{
    private static Latch instance;
    public CountDownLatch countDownLatch;

    private Latch()
    {
        countDownLatch = null;
    }

    public CountDownLatch setCountDownLatch(int count)
    {
        countDownLatch = new CountDownLatch(count);
        return countDownLatch;
    }
    public static Latch getLatch()
    {
        if(instance == null)
        {
            instance = new Latch();
        }
        return instance;
    }
}
