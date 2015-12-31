package hr.evolaris.air.foi.evolaris_smarttourism.db.distance_service;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistanceClass {

    @SerializedName("destination_addresses")
    @Expose
    public List<String> destinationAddresses = new ArrayList<String>();

    @SerializedName("origin_addresses")
    @Expose
    public List<String> originAddresses = new ArrayList<String>();

    @SerializedName("rows")
    @Expose
    public List<Row> rows = new ArrayList<Row>();

    @SerializedName("status")
    @Expose
    public String status;

}