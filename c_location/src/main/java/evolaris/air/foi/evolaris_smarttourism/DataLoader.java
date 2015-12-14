package evolaris.air.foi.evolaris_smarttourism;

import evolaris.air.foi.evolaris_smarttourism.ws.WebServiceAsyncTask;
import evolaris.air.foi.evolaris_smarttourism.ws.WebServiceParams;
import evolaris.air.foi.evolaris_smarttourism.ws.WebServiceResultHandler;

public class DataLoader
{
    WebServiceResultHandler resultHandler= new WebServiceResultHandler()
    {
        @Override
        public void handleResult(String result, boolean ok, long timestamp)
        {
            if(ok)
            {
                try
                {
                    JsonAdapter.getLocations(result);
                    for(;;)
                    {

                    }
                }
                catch (Exception exception)
                {
                    
                }
            }
        }
    };

    public void LoadData()
    {
        WebServiceAsyncTask asyncTask = new WebServiceAsyncTask();
        WebServiceParams webServiceParams = new WebServiceParams();

        webServiceParams.resultHandler = resultHandler;
        webServiceParams.targetAttribute = "results";
        webServiceParams.searchType = "";
        webServiceParams.outputFormat = "";
        webServiceParams.longitude = "";
        webServiceParams.latitude = "";
        webServiceParams.radius = "";
        webServiceParams.type = "";
    }
}
