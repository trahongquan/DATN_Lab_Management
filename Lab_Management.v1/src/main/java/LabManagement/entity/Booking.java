package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lab_id", nullable = false)
    private int labid;

    @Column(name = "content_id", nullable = false)
    private int contentid;

    @Column(name = "booking_date", nullable = false)
    private Date booking_Date;

    @Column(name = "comfirm_status", nullable = false)
    private String confirm_Status;

    @Column(name = "work_times", nullable = false)
    private int work_times;

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "is_delete", nullable = false)
    private int is_delete;

    public Booking() {
    }

    public Booking(int labid, int contentid, Date booking_Date, String confirm_Status, int work_times, String note, int is_delete) {
        this.labid = labid;
        this.contentid = contentid;
        this.booking_Date = booking_Date;
        this.confirm_Status = confirm_Status;
        this.work_times = work_times;
        this.note = note;
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", lab_id=" + labid +
                ", content_id=" + contentid +
                ", booking_Date=" + booking_Date +
                ", confirm_Status='" + confirm_Status + '\'' +
                ", work_times=" + work_times +
                ", note='" + note + '\'' +
                ", is_delete=" + is_delete +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabid() {
        return labid;
    }

    public void setLabid(int lab_id) {
        this.labid = lab_id;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int content_id) {
        this.contentid = content_id;
    }

    public Date getBooking_Date() {
        return booking_Date;
    }

    public void setBooking_Date(Date booking_Date) {
        this.booking_Date = booking_Date;
    }

    public String getConfirm_Status() {
        return confirm_Status;
    }

    public void setConfirm_Status(String confirm_Status) {
        this.confirm_Status = confirm_Status;
    }

    public int getWork_times() {
        return work_times;
    }

    public void setWork_times(int work_times) {
        this.work_times = work_times;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }
}

