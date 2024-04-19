package LabManagement.entity;

import LabManagement.ClassSuport.ToList;
import LabManagement.service.equipmentLab.EquipmentLabService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "equipment_lab")
public class EquipmentLab implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lab_id", nullable = false)
    private int labId;

    @Column(name = "equi_id", nullable = false)
    private int equipmentId;

    @Column(name = "equi_series", nullable = false)
    private String equipmentSeries;

    public EquipmentLab() {
    }

    public EquipmentLab(int labId, int equipmentId, List<String> equipmentSeriesList) {
        this.labId = labId;
        this.equipmentId = equipmentId;
        this.equipmentSeries = equipmentSeriesList.toString();
    }

    @Override
    public String toString() {
        return "EquipmentLab{" +
                "id=" + id +
                ", labId=" + labId +
                ", equipmentId=" + equipmentId +
                ", equipmentSeries='" + equipmentSeries + '\'' +
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

    public List<String> getEquipmentSerieList() {
        return new ToList().StringToList(equipmentSeries);
    }

    public void AddSeri(EquipmentLabService equipmentLabService, String seri){
        List<String> series = new ToList().StringToList(equipmentSeries);
        series.add(seri);
        this.setEquipmentSeries(series.toString());
        equipmentLabService.saveEquipmentLab(this);
    }
    public void DelSeri(EquipmentLabService equipmentLabService, String seri){
        List<String> series = new ToList().StringToList(equipmentSeries);
        series.remove(seri);
        this.setEquipmentSeries(series.toString());
        equipmentLabService.saveEquipmentLab(this);
    }
}
