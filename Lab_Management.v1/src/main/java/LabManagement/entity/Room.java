package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "leader_id", nullable = false)
    private int leaderId;

    @Column(name = "is_deleted", nullable = false)
    private int isDeleted;

    public Room() {
    }

    public Room(String roomName, int capacity, String location, int leaderId, int isDeleted) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.location = location;
        this.leaderId = leaderId;
        this.isDeleted = isDeleted;
    }
    // Getters and Setters (omitted for brevity)

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

}

