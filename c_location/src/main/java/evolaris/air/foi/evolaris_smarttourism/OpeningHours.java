package evolaris.air.foi.evolaris_smarttourism;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OpeningHours
{
    @SerializedName("open_now")
    @Expose
    public Boolean open_now;

    @SerializedName("weekday_text")
    @Expose
    public List<Object> weekday_text = new ArrayList<Object>();
}
