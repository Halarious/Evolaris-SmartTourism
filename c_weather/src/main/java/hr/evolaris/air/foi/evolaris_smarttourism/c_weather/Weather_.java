package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather_
{
    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("main")
    @Expose
    public String main;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("icon")
    @Expose
    public String icon;
}
