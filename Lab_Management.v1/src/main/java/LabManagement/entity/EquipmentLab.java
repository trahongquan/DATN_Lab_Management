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

    @Column(name = "levels", nullable = false)
    private String levels;

    public EquipmentLab() {
    }

    public EquipmentLab(int labId, int equipmentId, List<String> equipmentSeriesList, List<String> levels) {
        this.labId = labId;
        this.equipmentId = equipmentId;
        this.equipmentSeries = equipmentSeriesList.toString();
        this.levels = levels.toString();
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

    public List<String> getLevelList() {
        return new ToList().StringLevelsToList(levels);
    }

    public void setLevelList(String levels) {
        this.levels = levels;
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


    public void AddLevel(EquipmentLabService equipmentLabService, String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.add(level);
        this.setLevelList(levelList.toString());
        equipmentLabService.saveEquipmentLab(this);
    }
    public void DelLevel(EquipmentLabService equipmentLabService, String level){
        List<String> levelList = new ToList().StringToList(levels);
        levelList.remove(level);
        this.setLevelList(levelList.toString());
        equipmentLabService.saveEquipmentLab(this);
    }
}
