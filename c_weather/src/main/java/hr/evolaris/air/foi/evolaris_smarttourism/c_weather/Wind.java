package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind
{
    @SerializedName("speed")
    @Expose
    public Double speed;
    @SerializedName("deg")
    @Expose
    public Double deg;
}
