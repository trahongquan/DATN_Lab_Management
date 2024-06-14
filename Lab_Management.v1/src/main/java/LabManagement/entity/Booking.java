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
    private Date bookingDate;

    @Column(name = "comfirm_status", nullable = false)
    private String confirmStatus;

    @Column(name = "work_times", nullable = false)
    private int work_times;

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "is_delete", nullable = false)
    private int is_delete;

    @Column(name = "auto", nullable = false)
    private String auto;

    @Column(name = "comfirm_used", nullable = false)
    private String comfirmUsed;

    public Booking() {
    }

    public Booking(int labid, int contentid, Date bookingDate, String confirmStatus, int work_times, String note, int is_delete, String auto, String comfirmUsed) {
        this.labid = labid;
        this.contentid = contentid;
        this.bookingDate = bookingDate;
        this.confirmStatus = confirmStatus;
        this.work_times = work_times;
        this.note = note;
        this.is_delete = is_delete;
        this.auto = auto;
        this.comfirmUsed = comfirmUsed;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", lab_id=" + labid +
                ", content_id=" + contentid +
                ", booking_Date=" + bookingDate +
                ", confirm_Status='" + confirmStatus + '\'' +
                ", work_times=" + work_times +
                ", note='" + note + '\'' +
                ", is_delete=" + is_delete +
                ", auto=" + auto +
                ", comfirmUsed=" + comfirmUsed +
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date booking_Date) {
        this.bookingDate = booking_Date;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirm_Status) {
        this.confirmStatus = confirm_Status;
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

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getComfirmUsed() {
        return comfirmUsed;
    }

    public void setComfirmUsed(String comfirmUsed) {
        this.comfirmUsed = comfirmUsed;
    }
}

