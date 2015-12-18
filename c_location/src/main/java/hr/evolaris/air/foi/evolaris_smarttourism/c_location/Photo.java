package hr.evolaris.air.foi.evolaris_smarttourism.c_location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Photo
{
    @SerializedName("height")
    @Expose
    public Integer height;

    @SerializedName("html_attributions")
    @Expose
    public List<String> html_attributions = new ArrayList<String>();

    @SerializedName("photo_reference")
    @Expose
    public String photo_reference;

    @SerializedName("width")
    @Expose
    public Integer width;
}
