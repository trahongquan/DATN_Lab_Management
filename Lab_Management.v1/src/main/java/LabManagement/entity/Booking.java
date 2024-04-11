package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_id", nullable = false)
    private int roomId;

    @Column(name = "people_id", nullable = false)
    private int peopleId;

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    @Column(name = "booking_status", nullable = false)
    private int bookingStatus;

    @Column(name = "confirm_status", nullable = false)
    private String confirmStatus;

    public Booking() {
    }

    public Booking(int roomId, int peopleId, Date bookingDate, int bookingStatus, String confirmStatus) {
        this.roomId = roomId;
        this.peopleId = peopleId;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.confirmStatus = confirmStatus;
    }
    // Getters and Setters (omitted for brevity)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", peopleId=" + peopleId +
                ", bookingDate=" + bookingDate +
                ", bookingStatus=" + bookingStatus +
                ", confirmStatus='" + confirmStatus + '\'' +
                '}';
    }
}

