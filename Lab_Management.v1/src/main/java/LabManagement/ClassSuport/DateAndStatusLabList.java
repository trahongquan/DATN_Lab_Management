package LabManagement.ClassSuport;

import java.sql.Date;
import java.util.List;

public class DateAndStatusLabList {
    private List<DateAndStatusLab> dateAndStatusLabs;

    public DateAndStatusLabList(List<DateAndStatusLab> dateAndStatusLabs) {
        this.dateAndStatusLabs = dateAndStatusLabs;
    }

    @Override
    public String toString() {
        return "DateAndStatusLabList{" +
                "dateAndStatusLabs=" + dateAndStatusLabs +
                '}';
    }

    public List<DateAndStatusLab> getDateAndStatusLabs() {
        return dateAndStatusLabs;
    }

    public void setDateAndStatusLabs(List<DateAndStatusLab> dateAndStatusLabs) {
        this.dateAndStatusLabs = dateAndStatusLabs;
    }
}
