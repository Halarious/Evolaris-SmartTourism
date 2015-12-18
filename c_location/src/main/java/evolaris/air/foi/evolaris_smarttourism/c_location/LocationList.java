package evolaris.air.foi.evolaris_smarttourism.c_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LocationList
{
    @SerializedName("html_attributions")
    @Expose
    public List<Object> html_attributions = new ArrayList<Object>();

    @SerializedName("next_page_token")
    @Expose
    public String next_page_token;

    @SerializedName("results")
    @Expose
    public List<evolaris.air.foi.evolaris_smarttourism.c_location.Location> results = new ArrayList<evolaris.air.foi.evolaris_smarttourism.c_location.Location>();

    @SerializedName("status")
    @Expose
    public String status;
}
