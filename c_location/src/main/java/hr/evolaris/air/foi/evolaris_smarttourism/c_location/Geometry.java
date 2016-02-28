package hr.evolaris.air.foi.evolaris_smarttourism.c_location;

import android.location.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry
{
    @SerializedName("location")
    @Expose
    public LocationCoords location;
}
