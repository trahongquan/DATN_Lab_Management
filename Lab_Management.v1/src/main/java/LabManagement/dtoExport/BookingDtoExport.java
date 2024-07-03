package LabManagement.dtoExport;

import LabManagement.dto.ContentDTO;
import LabManagement.dto.LabDTO;
import LabManagement.entity.*;

import java.sql.Date;
import java.util.List;

public class BookingDtoExport {
    private int id;
    private String labName;
    private String contentName;
    private String className;
    private String reservationistName;
    private int work_times;
    private int amountOfPeople;
    private Date booking_Date;
    private String note;
    private String experimentType;
    private String experimentReport;
    private String confirm_Status;
    private String comfirmUsed;
    private String auto;

    public BookingDtoExport() {
    }

    public BookingDtoExport(Booking booking, String labName, Content content, String experimentType, String experimentReport, String reservationistName) {
        this.id = booking.getId();
        this.labName = labName;
        this.contentName = content.getName();
        this.className = content.getClassName();
        this.reservationistName = reservationistName;
        this.work_times = booking.getWork_times();
        this.amountOfPeople = content.getAmountOfPeople();
        this.booking_Date = booking.getBookingDate();
        this.note = booking.getNote();
        this.experimentType = experimentType;
        this.experimentReport = experimentReport;
        this.confirm_Status = booking.getConfirmStatus();
        this.comfirmUsed = booking.getConfirmUsed();
        this.auto = booking.getAuto();
    }

    @Override
    public String toString() {
        return "BookingDtoExport{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
                ", contentName='" + contentName + '\'' +
                ", className='" + className + '\'' +
                ", reservationistName='" + reservationistName + '\'' +
                ", work_times=" + work_times +
                ", amountOfPeople=" + amountOfPeople +
                ", booking_Date=" + booking_Date +
                ", note='" + note + '\'' +
                ", experimentType='" + experimentType + '\'' +
                ", experimentReport='" + experimentReport + '\'' +
                ", confirm_Status='" + confirm_Status + '\'' +
                ", comfirmUsed='" + comfirmUsed + '\'' +
                ", auto='" + auto + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getReservationistName() {
        return reservationistName;
    }

    public void setReservationistName(String reservationistName) {
        this.reservationistName = reservationistName;
    }

    public int getWork_times() {
        return work_times;
    }

    public void setWork_times(int work_times) {
        this.work_times = work_times;
    }

    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    public Date getBooking_Date() {
        return booking_Date;
    }

    public void setBooking_Date(Date booking_Date) {
        this.booking_Date = booking_Date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(String experimentType) {
        this.experimentType = experimentType;
    }

    public String getExperimentReport() {
        return experimentReport;
    }

    public void setExperimentReport(String experimentReport) {
        this.experimentReport = experimentReport;
    }

    public String getConfirm_Status() {
        return confirm_Status;
    }

    public void setConfirm_Status(String confirm_Status) {
        this.confirm_Status = confirm_Status;
    }

    public String getComfirmUsed() {
        return comfirmUsed;
    }

    public void setComfirmUsed(String comfirmUsed) {
        this.comfirmUsed = comfirmUsed;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }
}
