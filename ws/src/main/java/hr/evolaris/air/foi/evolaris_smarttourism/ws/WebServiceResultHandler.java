package hr.evolaris.air.foi.evolaris_smarttourism.ws;

public interface WebServiceResultHandler
{
    public void handleResult(
            String result,
            boolean ok,
            long timestamp
    );
}
