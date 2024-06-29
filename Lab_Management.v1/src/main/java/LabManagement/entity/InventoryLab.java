package LabManagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "inventory_lab")
public class InventoryLab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lab_id")
    private int labId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "equipment_lab_data", columnDefinition = "TEXT")
    private String equipmentLabData;

    public InventoryLab(int labId, Integer year, String equipmentLabData) {
        this.labId = labId;
        this.year = year;
        this.equipmentLabData = equipmentLabData;
    }

    public InventoryLab() {
    }

    @Override
    public String toString() {
        return "InventoryLab{" +
                "id=" + id +
                ", labId=" + labId +
                ", year=" + year +
                ", equipmentLabData='" + equipmentLabData + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEquipmentLabData() {
        return equipmentLabData;
    }

    public void setEquipmentLabData(String equipmentLabData) {
        this.equipmentLabData = equipmentLabData;
    }
}