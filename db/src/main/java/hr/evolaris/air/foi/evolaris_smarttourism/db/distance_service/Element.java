package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Element {

    @SerializedName("distance")
    @Expose
    public Distance distance;

    @SerializedName("duration")
    @Expose
    public Duration duration;

    @SerializedName("status")
    @Expose
    public String status;

}