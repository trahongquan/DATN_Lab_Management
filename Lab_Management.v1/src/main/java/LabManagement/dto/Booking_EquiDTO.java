package LabManagement.dto;

import LabManagement.ClassSuport.ToList;
import LabManagement.entity.Booking_equi;
import LabManagement.entity.Equipment;

import java.util.List;

public class Booking_EquiDTO {
    private int id;
    private int equipmentId;
    private String equipmentSeries;
    private int bookingId;
    private Equipment equipment;

    public Booking_EquiDTO() {
    }

    public Booking_EquiDTO(Booking_equi booking_equi, Equipment equipment) {
        this.id = booking_equi.getId();
        this.equipmentId = booking_equi.getEquipmentId();
        this.equipmentSeries = booking_equi.getEquipmentSeries();
        this.bookingId = booking_equi.getBookingId();
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Booking_EquiDTO{" +
                "id=" + id +
                ", equipmentId=" + equipmentId +
                ", equipmentSeries='" + equipmentSeries + '\'' +
                ", bookingId=" + bookingId +
                ", equipment=" + equipment +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentSeries() {
        return equipmentSeries;
    }

    public void setEquipmentSeries(String equipmentSeries) {
        this.equipmentSeries = equipmentSeries;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public List<String> getEquipmentSerieList(){
        return new ToList().StringToList(equipmentSeries);
    }

    public void setEquipmentSerieList(List<String> series){
        equipmentSeries = series.toString();
    }

    public int getQuantityBookingEqui(){
        return this.getEquipmentSerieList().size();
    }

}
