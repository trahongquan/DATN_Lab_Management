package LabManagement.dto;

import LabManagement.entity.*;

import java.sql.Date;
import java.util.List;

public class BookingDTO {
    private int id;
    private int labid;
    private int content_id;
    private Date booking_Date;
    private String confirm_Status;
    private int work_times;
    private String note;
    private int is_delete;
    private String auto;
    private String comfirmUsed;

    private Content content;
    private ContentDTO contentDTO;
    private Lab lab;
    private LabDTO labDTO;
    private ExperimentReport experimentReport;
    private List<Booking_equi> booking_equis;

    public BookingDTO() {
    }

    public BookingDTO(Booking booking, Content content, Lab lab, ExperimentReport experimentReport) {
        this.id = booking.getId();
        this.labid = booking.getLabid();
        this.content_id = booking.getContentid();
        this.booking_Date = booking.getBookingDate();
        this.confirm_Status = booking.getConfirmStatus();
        this.work_times = booking.getWork_times();
        this.note = booking.getNote();
        this.is_delete = booking.getIs_delete();
        this.auto = booking.getAuto();
        this.comfirmUsed = booking.getComfirmUsed();
        this.content = content;
        this.lab = lab;
        this.experimentReport = experimentReport;
    }

    public BookingDTO(Booking booking, ContentDTO contentDTO, LabDTO labDTO, List<Booking_equi> booking_equis) {
        this.id = booking.getId();
        this.labid = booking.getLabid();
        this.content_id = booking.getContentid();
        this.booking_Date = booking.getBookingDate();
        this.confirm_Status = booking.getConfirmStatus();
        this.work_times = booking.getWork_times();
        this.note = booking.getNote();
        this.is_delete = booking.getIs_delete();
        this.auto = booking.getAuto();
        this.comfirmUsed = booking.getComfirmUsed();
        this.contentDTO = contentDTO;
        this.labDTO = labDTO;
        this.booking_equis = booking_equis;
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
                ", auto=" + auto +
                ", comfirmUsed=" + comfirmUsed +
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

    public ContentDTO getContentDTO() {
        return contentDTO;
    }

    public void setContentDTO(ContentDTO contentDTO) {
        this.contentDTO = contentDTO;
    }

    public LabDTO getLabDTO() {
        return labDTO;
    }

    public void setLabDTO(LabDTO labDTO) {
        this.labDTO = labDTO;
    }

    public List<Booking_equi> getBooking_equis() {
        return booking_equis;
    }

    public void setBooking_equis(List<Booking_equi> booking_equis) {
        this.booking_equis = booking_equis;
    }
}
