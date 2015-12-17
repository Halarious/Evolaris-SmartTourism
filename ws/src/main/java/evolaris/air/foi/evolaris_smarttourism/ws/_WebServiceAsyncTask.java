package evolaris.air.foi.evolaris_smarttourism.ws;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class _WebServiceAsyncTask
{
    WebServiceResultHandler listener;
    Retrofit retrofit;

    public _WebServiceAsyncTask(WebServiceResultHandler listener)
    {
        this.listener = listener;

        OkHttpClient httpClient = new OkHttpClient();

        this.retrofit = new Retrofit.Builder()
                            .baseUrl("http://api.openweathermap.org/data/2.5/weather?")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(httpClient)
                            .build();
    }

    public void
    getWeather()
    {
        ServiceCaller serviceCaller = retrofit.create(ServiceCaller.class);

        Call<ResponseBody> call = null;
    }
}
