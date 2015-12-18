package evolaris.air.foi.evolaris_smarttourism.c_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationCoords
{
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;
}
