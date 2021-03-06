package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main
{
    @SerializedName("temp")
    @Expose
    public Double temp;

    @SerializedName("pressure")
    @Expose
    public Double pressure;

    @SerializedName("humidity")
    @Expose
    public Integer humidity;

    @SerializedName("temp_min")
    @Expose
    public Double temp_min;

    @SerializedName("temp_max")
    @Expose
    public Double temp_max;
}
