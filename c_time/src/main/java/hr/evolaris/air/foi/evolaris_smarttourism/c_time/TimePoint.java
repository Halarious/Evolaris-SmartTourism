package hr.evolaris.air.foi.evolaris_smarttourism.c_time;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimePoint
{
    private static final String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                                          "Saturday", "Sunday" };
    private static final String[] months = {"January", "February", "March", "April", "May", "June",
                                            "July", "August", "September", "October", "November",
                                            "December"};
    public String dayName;
    public String monthName;
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;
    public int second;

    public TimePoint()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        dayName = days[calendar.get(Calendar.DAY_OF_WEEK)];
        monthName = months[month];

    }
}
