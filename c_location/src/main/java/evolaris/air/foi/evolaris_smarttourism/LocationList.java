package evolaris.air.foi.evolaris_smarttourism;

import android.location.*;

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
    public List<Location> results = new ArrayList<Location>();

    @SerializedName("status")
    @Expose
    public String status;
}
