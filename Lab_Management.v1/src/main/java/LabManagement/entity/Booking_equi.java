package LabManagement.entity;

import LabManagement.ClassSuport.ToList;
import LabManagement.service.booking_equi.Booking_equiService;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "booking_equi")
public class Booking_equi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "equi_id", nullable = false)
    private int equipmentId;

    @Column(name = "equi_series", nullable = false)
    private String equipmentSeries;

    @Column(name = "booking_id", nullable = false)
    private int bookingId;

    public Booking_equi() {
    }

    public Booking_equi(int equipmentId, String equipmentSeries, int bookingId) {
        this.equipmentId = equipmentId;
        this.equipmentSeries = equipmentSeries;
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "BookingEquipment{" +
                "id=" + id +
                ", equipmentId=" + equipmentId +
                ", equipmentSeries='" + equipmentSeries + '\'' +
                ", bookingId='" + bookingId + '\'' +
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

    public List<String> getEquipmentSerieList(){
        return new ToList().StringToList(equipmentSeries);
    }

    public void setEquipmentSerieList(List<String> series){
        equipmentSeries = series.toString();
    }

    public void AddSeri(Booking_equiService booking_equiService, String seri){
        List<String> series = new ToList().StringToList(equipmentSeries);
        series.add(seri);
        this.setEquipmentSeries(series.toString());
        booking_equiService.saveBookingEquipment(this);
    }
    public void DelSeri(Booking_equiService booking_equiService, String seri){
        List<String> series = new ToList().StringToList(equipmentSeries);
        series.remove(seri);
        this.setEquipmentSeries(series.toString());
        booking_equiService.saveBookingEquipment(this);
    }
}
