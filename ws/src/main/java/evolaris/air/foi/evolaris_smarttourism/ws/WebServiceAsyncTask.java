package evolaris.air.foi.evolaris_smarttourism.ws;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceAsyncTask
        extends AsyncTask <WebServiceParams, Void, AsyncTaskInnerResult>
{

    @Override
    protected AsyncTaskInnerResult doInBackground(WebServiceParams[] params)
    {
        AsyncTaskInnerResult asyncTaskInnerResult = new AsyncTaskInnerResult();

        String googleApiKey = "AIzaSyAPGo4La9l47-J2kc180bpAGevLu7d-4Sk";

        String urlString = "https://maps.googleapis.com/maps/api/place/"
                            + params[0].searchType + params[0].outputFormat
                            + "?";

        String urlParameters = "";
        urlParameters += "location=" + params[0].longitude + params[0].latitude;
        urlParameters += "&radius" + params[0].radius;
        urlParameters += "&types" + params[0].type;

        urlParameters += "&key=";
        urlParameters += googleApiKey;

        byte[] postData = urlParameters.getBytes();

        HttpURLConnection WebServiceConnection = null;

        try
        {
            URL url = new URL(urlString);
            WebServiceConnection = (HttpURLConnection) url.openConnection();
            WebServiceConnection.setDoOutput(true);
            WebServiceConnection.setDoInput(true);
            WebServiceConnection.setUseCaches(false);
            WebServiceConnection.setRequestMethod("POST");
            WebServiceConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            WebServiceConnection.setRequestProperty("charset", "utf-8");
            WebServiceConnection.setRequestProperty("Content-Length", "" + postData.length);

            DataOutputStream dw = new DataOutputStream(WebServiceConnection.getOutputStream());
            dw.write(postData);

            InputStream is = new BufferedInputStream(WebServiceConnection.getInputStream());
            BufferedReader dr = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = dr.readLine()) != null)
            {
                asyncTaskInnerResult.webServiceResult += line;
            }
        }

        catch(IOException Exception)
        {
            Exception.printStackTrace();
        }
        finally
        {
            if(WebServiceConnection != null)
            {
                WebServiceConnection.disconnect();
            }
        }

        asyncTaskInnerResult.webServiceHandler = params[0].resultHandler;
        asyncTaskInnerResult.targetAttribute = params[0].targetAttribute;

        return asyncTaskInnerResult;
    }

    @Override
    protected void onPostExecute(AsyncTaskInnerResult asyncTaskInnerResult)
    {
        super.onPostExecute(asyncTaskInnerResult);

        String Output = "";
        boolean ok = false;
        long timestamp = 0;

        String webServiceResult = asyncTaskInnerResult.webServiceResult;
        if(webServiceResult != "")
        {
            try
            {
                JSONObject jsonObject = new JSONObject(webServiceResult);
                if(jsonObject.has("status"))
                {
                    if(jsonObject.getString("status").equals("OK"))
                    {
                        Output = jsonObject.getString(asyncTaskInnerResult.targetAttribute);
                        ok = true;
                    }
                    else
                    {
                        Output = "Failed. Unknown error";
                    }
                }
            }
            catch(JSONException exception)
            {

            }
        }
    }
}
