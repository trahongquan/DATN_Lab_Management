package LabManagement.dto;

import LabManagement.entity.Booking;
import LabManagement.entity.People;

import java.sql.Date;
import java.util.List;

public class LabINDEX {
    private int id;
    private String labName;
    private int capacity;
    private String location;
    private int lab_managemet_id;
    private int isDeleted;
    private People lab_managemet;
    List<Booking> bookings;
    List<Date> Datebookings;
}
