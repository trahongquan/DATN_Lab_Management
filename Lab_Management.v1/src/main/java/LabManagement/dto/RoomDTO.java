package LabManagement.dto;

import LabManagement.entity.Booking;
import LabManagement.entity.People;
import LabManagement.entity.Room;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class RoomDTO {
    private int id;
    private String roomName;
    private int capacity;
    private String location;
    private int leaderId;
    private int isDeleted;
    private People leader;
    List<Booking> bookings;
    List<Date> Datebookings;

    public RoomDTO() {
    }

    public RoomDTO(int id, String roomName, int capacity, String location, int leaderId, int isDeleted, People leader) {
        this.id = id;
        this.roomName = roomName;
        this.capacity = capacity;
        this.location = location;
        this.leaderId = leaderId;
        this.isDeleted = isDeleted;
        this.leader = leader;
    }
    public RoomDTO(Room room, People leader) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.capacity = room.getCapacity();
        this.location = room.getLocation();
        this.leaderId = room.getLeaderId();
        this.isDeleted = room.getIsDeleted();
        this.leader = leader;
    }
    public RoomDTO(Room room, People leader, List<Booking> bookings) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.capacity = room.getCapacity();
        this.location = room.getLocation();
        this.leaderId = room.getLeaderId();
        this.isDeleted = room.getIsDeleted();
        this.leader = leader;
        this.bookings = bookings;
    }

    public RoomDTO(Room room, List<Date> Datebookings, People leader) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.capacity = room.getCapacity();
        this.location = room.getLocation();
        this.leaderId = room.getLeaderId();
        this.isDeleted = room.getIsDeleted();
        this.leader = leader;
        this.Datebookings = Datebookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public People getLeader() {
        return leader;
    }

    public void setLeader(People leader) {
        this.leader = leader;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Date> getDatebookings() {
        return Datebookings;
    }

    public void setDatebookings(List<Date> datebookings) {
        Datebookings = datebookings;
    }
}
