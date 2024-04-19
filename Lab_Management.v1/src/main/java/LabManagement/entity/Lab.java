package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lab")
public class Lab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lab_name", nullable = false)
    private String labName;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "lab_managemet_id", nullable = false)
    private int lab_managemet_id;

    @Column(name = "is_delete", nullable = false)
    private int isDelete;

    public Lab() {
    }

    public Lab(String labName, int capacity, String location, int lab_managemet_id, int isDelete) {
        this.labName = labName;
        this.capacity = capacity;
        this.location = location;
        this.lab_managemet_id = lab_managemet_id;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                ", lab_managemet_id=" + lab_managemet_id +
                ", isDeleted=" + isDelete +
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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDeleted) {
        this.isDelete = isDeleted;
    }
}

