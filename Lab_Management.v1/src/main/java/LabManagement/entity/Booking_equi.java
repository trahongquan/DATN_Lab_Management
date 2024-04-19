package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
    private String bookingId;

    public Booking_equi() {
    }

    public Booking_equi(int equipmentId, String equipmentSeries, String bookingId) {
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
