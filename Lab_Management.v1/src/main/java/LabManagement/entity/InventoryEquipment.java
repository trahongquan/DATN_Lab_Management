package LabManagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "inventory_equipment")
public class InventoryEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "equipment_data", columnDefinition = "TEXT")
    private String equipmentData;

    public InventoryEquipment(Integer year, String equipmentData) {
        this.year = year;
        this.equipmentData = equipmentData;
    }

    public InventoryEquipment() {
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEquipmentData() {
        return equipmentData;
    }

    public void setEquipmentData(String equipmentData) {
        this.equipmentData = equipmentData;
    }

    @Override
    public String toString() {
        return "InventoryEquipment{" +
                "id=" + id +
                ", year=" + year +
                ", equipmentData='" + equipmentData + '\'' +
                '}';
    }
}
