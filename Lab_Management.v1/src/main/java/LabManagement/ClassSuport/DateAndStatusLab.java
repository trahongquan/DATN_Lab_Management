package LabManagement.ClassSuport;

import java.sql.Date;

public class DateAndStatusLab {
    private Date date;
    private String status;

    public DateAndStatusLab(Date date, String status) {
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "DateAndStatusLab{" +
                "date=" + date +
                ", status='" + status + '\'' +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
