package hr.evolaris.air.foi.evolaris_smarttourism.core;

import java.util.concurrent.CountDownLatch;

public class Latches
{
    private static Latches instance;
    public CountDownLatch fetchCountDownLatch;
    public CountDownLatch DistanceMatrixCountDownLatch;

    private Latches()
    {
        fetchCountDownLatch = null;
        DistanceMatrixCountDownLatch = null;
    }

    public CountDownLatch setFetchCountDownLatch(int count)
    {
        fetchCountDownLatch = new CountDownLatch(count);
        return fetchCountDownLatch;
    }

    public CountDownLatch setDistanceMatrixCountDownLatch(int count)
    {
        DistanceMatrixCountDownLatch = new CountDownLatch(count);
        return DistanceMatrixCountDownLatch;
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
