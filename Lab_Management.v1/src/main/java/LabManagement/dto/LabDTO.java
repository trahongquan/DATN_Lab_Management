package LabManagement.dto;

import LabManagement.ClassSuport.DateAndStatusLab;
import LabManagement.entity.Booking;
import LabManagement.entity.EquipmentLab;
import LabManagement.entity.Lab;
import LabManagement.entity.People;

import java.sql.Date;
import java.util.List;

public class LabDTO {
    private int id;
    private String labName;
    private int capacity;
    private String location;
    private int lab_managemet_id;
    private int isDeleted;
    private People lab_managemet;
    List<Booking> bookings;
    List<EquipmentLab> equipmentLabs;
    List<DateAndStatusLab> dateAndStatusLab;

    public LabDTO() {
    }

    public LabDTO(Lab lab, People lab_managemet) {
        this.id = lab.getId();
        this.labName = lab.getLabName();
        this.capacity = lab.getCapacity();
        this.location = lab.getLocation();
        this.lab_managemet_id = lab.getLab_managemet_id();
        this.isDeleted = lab.getIsDelete();
        this.lab_managemet = lab_managemet;
    }

    public LabDTO(Lab lab, List<EquipmentLab> equipmentLabs, People lab_managemet) {
        this.id = lab.getId();
        this.labName = lab.getLabName();
        this.capacity = lab.getCapacity();
        this.location = lab.getLocation();
        this.lab_managemet_id = lab.getLab_managemet_id();
        this.isDeleted = lab.getIsDelete();
        this.lab_managemet = lab_managemet;
        this.equipmentLabs = equipmentLabs;
    }

    public LabDTO(Lab lab, People lab_managemet, List<DateAndStatusLab> dateAndStatusLab) {
        this.id = lab.getId();
        this.labName = lab.getLabName();
        this.capacity = lab.getCapacity();
        this.location = lab.getLocation();
        this.lab_managemet_id = lab.getLab_managemet_id();
        this.isDeleted = lab.getIsDelete();
        this.lab_managemet = lab_managemet;
        this.dateAndStatusLab = dateAndStatusLab;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLab_managemet_id() {
        return lab_managemet_id;
    }

    public void setLab_managemet_id(int lab_managemet_id) {
        this.lab_managemet_id = lab_managemet_id;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public People getLab_managemet() {
        return lab_managemet;
    }

    public void setLab_managemet(People lab_managemet) {
        this.lab_managemet = lab_managemet;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<EquipmentLab> getEquipmentLabs() {
        return equipmentLabs;
    }

    public void setEquipmentLabs(List<EquipmentLab> equipmentLabs) {
        this.equipmentLabs = equipmentLabs;
    }

    public List<DateAndStatusLab> getDateAndStatusLab() {
        return dateAndStatusLab;
    }

    public void setDateAndStatusLab(List<DateAndStatusLab> dateAndStatusLab) {
        this.dateAndStatusLab = dateAndStatusLab;
    }
}
