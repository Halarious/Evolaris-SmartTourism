import java.util.Date;

/**
 * Created by Fonz Yáñez on 12/10/2015.
 */
public class Museum extends TourismUnit {

    private Date startDate;
    private Date endDate;

    public Museum(Integer id, String name, String description, Date startDate, Date endDate) {
        super(id, name, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
