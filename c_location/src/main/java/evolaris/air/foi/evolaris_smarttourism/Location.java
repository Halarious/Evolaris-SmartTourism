package evolaris.air.foi.evolaris_smarttourism;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Location
{
    @SerializedName("geometry")
    @Expose
    public Geometry geometry;

    @SerializedName("icon")
    @Expose
    public String icon;

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("place_id")
    @Expose
    public String place_id;

    @SerializedName("rating")
    @Expose
    public Double rating;

    @SerializedName("reference")
    @Expose
    public String reference;

    @SerializedName("scope")
    @Expose
    public String scope;

    @SerializedName("types")
    @Expose
    public List<String> types = new ArrayList<String>();

    @SerializedName("vicinity")
    @Expose
    public String vicinity;

    @SerializedName("opening_hours")
    @Expose
    public OpeningHours openingHours;

    @SerializedName("photos")
    @Expose
    public List<Photo> photos = new ArrayList<Photo>();
}
