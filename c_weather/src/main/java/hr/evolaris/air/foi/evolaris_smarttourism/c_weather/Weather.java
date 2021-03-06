package hr.evolaris.air.foi.evolaris_smarttourism.c_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Weather
{
    @SerializedName("coord")
    @Expose
    public Coord coord;

    @SerializedName("weather")
    @Expose
    public List<Weather_> weather = new ArrayList<Weather_>();

    @SerializedName("base")
    @Expose
    public String base;

    @SerializedName("main")
    @Expose
    public Main main;

    @SerializedName("wind")
    @Expose
    public Wind wind;

    @SerializedName("rain")
    @Expose
    public Rain rain;

    @SerializedName("clouds")
    @Expose
    public Clouds clouds;

    @SerializedName("dt")
    @Expose
    public Integer dt;

    @SerializedName("sys")
    @Expose
    public Sys sys;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("cod")
    @Expose
    public Integer cod;
}
