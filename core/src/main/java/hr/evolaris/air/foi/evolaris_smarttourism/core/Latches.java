package hr.evolaris.air.foi.evolaris_smarttourism.core;

import java.util.concurrent.CountDownLatch;

public class Latches
{
    private static Latches instance;
    public CountDownLatch fetchCountDownLatch;
    public CountDownLatch deliverCountDownLatch;

    private Latches()
    {
        fetchCountDownLatch = null;
        deliverCountDownLatch = null;
    }

    public CountDownLatch setFetchCountDownLatch(int count)
    {
        fetchCountDownLatch = new CountDownLatch(count);
        return fetchCountDownLatch;
    }

    public CountDownLatch setDeliverCountDownLatch(int count)
    {
        deliverCountDownLatch = new CountDownLatch(count);
        return deliverCountDownLatch;
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
