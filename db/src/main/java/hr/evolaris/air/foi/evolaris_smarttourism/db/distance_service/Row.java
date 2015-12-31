package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("elements")
    @Expose
    public List<Element> elements = new ArrayList<Element>();

}