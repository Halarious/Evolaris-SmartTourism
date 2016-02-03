package hr.evolaris.air.foi.evolaris_smarttourism.db;

public class RecommendationResult
{

    private static RecommendationResult instance;
    public int distance;
    public String address;

    private RecommendationResult()
    {
        distance = 0;
        address = null;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public static RecommendationResult getResult()
    {
        if(instance == null)
        {
            instance = new RecommendationResult();
        }
        return instance;
    }
}
