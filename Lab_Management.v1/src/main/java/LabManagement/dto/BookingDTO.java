package LabManagement.dto;

import LabManagement.entity.Booking;
import LabManagement.entity.Content;
import LabManagement.entity.ExperimentReport;
import LabManagement.entity.Lab;

import java.sql.Date;

public class BookingDTO {
    private int id;
    private int labid;
    private int content_id;
    private Date booking_Date;
    private String confirm_Status;
    private int work_times;
    private String note;
    private int is_delete;

    private Content content;
    private Lab lab;
    private ExperimentReport experimentReport;

    public BookingDTO() {
    }

    public BookingDTO(Booking booking, Content content, Lab lab, ExperimentReport experimentReport) {
        this.id = booking.getId();
        this.labid = booking.getLabid();
        this.content_id = booking.getContentid();
        this.booking_Date = booking.getBooking_Date();
        this.confirm_Status = booking.getConfirm_Status();
        this.work_times = booking.getWork_times();
        this.note = booking.getNote();
        this.is_delete = booking.getIs_delete();
        this.content = content;
        this.lab = lab;
        this.experimentReport = experimentReport;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", labid=" + labid +
                ", content_id=" + content_id +
                ", booking_Date=" + booking_Date +
                ", confirm_Status='" + confirm_Status + '\'' +
                ", work_times=" + work_times +
                ", note='" + note + '\'' +
                ", is_delete=" + is_delete +
                ", content=" + content +
                ", lab=" + lab +
                ", experimentReport=" + experimentReport +
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

    public void setLabid(int labid) {
        this.labid = labid;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public ExperimentReport getExperimentReport() {
        return experimentReport;
    }

    public void setExperimentReport(ExperimentReport experimentReport) {
        this.experimentReport = experimentReport;
    }
}
