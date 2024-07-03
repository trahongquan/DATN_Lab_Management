package LabManagement.dtoExport;

import LabManagement.ClassSuport.DateAndStatusLab;
import LabManagement.entity.Booking;
import LabManagement.entity.EquipmentLab;
import LabManagement.entity.Lab;
import LabManagement.entity.People;

import java.util.List;

public class LabDtoExport {
    private int id;
    private String labName;
    private String labMmanagemetName;
    private String managingUnit;
    private int capacity;
    private String location;

    public LabDtoExport() {
    }

    public LabDtoExport(Lab lab, String labMmanagemetName) {
        this.id = lab.getId();
        this.labName = lab.getLabName();
        this.capacity = lab.getCapacity();
        this.location = lab.getLocation();
        this.managingUnit = lab.getManagingUnit();
        this.labMmanagemetName = labMmanagemetName;
    }

    @Override
    public String toString() {
        return "LabDtoExport{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                ", managingUnit='" + managingUnit + '\'' +
                ", labMmanagemetName='" + labMmanagemetName + '\'' +
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

    public String getManagingUnit() {
        return managingUnit;
    }

    public void setManagingUnit(String managingUnit) {
        this.managingUnit = managingUnit;
    }

    public String getLabMmanagemetName() {
        return labMmanagemetName;
    }

    public void setLabMmanagemetName(String labMmanagemetName) {
        this.labMmanagemetName = labMmanagemetName;
    }
}
