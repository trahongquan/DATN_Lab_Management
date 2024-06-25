package LabManagement.dto;

import LabManagement.ClassSuport.ToList;
import LabManagement.entity.Equipment;
import LabManagement.entity.EquipmentLab;

import java.util.List;

public class EquipmentLabDTO {
    private int id;

    private int labId;

    private int equipmentId;

    private Equipment equipment;

    private String name;

    private String equipmentSeries;

    private List<String> equipmentSerieList;

    private List<String> levels;

    private List<String> usingdates;

    public EquipmentLabDTO() {
    }

    public EquipmentLabDTO(int id, int labId, Equipment equipment, List<String> equipmentSerieList) {
        this.id = id;
        this.labId = labId;
        this.equipmentId = equipment.getId();
        this.equipment = equipment;
        this.equipmentSeries = equipmentSerieList.toString();
        this.equipmentSerieList = equipmentSerieList;
    }
    public EquipmentLabDTO(EquipmentLab equipmentLab, Equipment equipment, List<String> equipmentSerieList, List<String> levels, List<String> usingdates) {
        this.id = equipmentLab.getId();
        this.labId = equipmentLab.getLabId();
        this.equipmentId = equipmentLab.getEquipmentId();
        this.equipment = equipment;
        this.equipmentSeries = equipmentSerieList.toString();
        this.equipmentSerieList = equipmentSerieList;
        this.levels = levels;
        this.usingdates = usingdates;
    }
    public EquipmentLabDTO(EquipmentLab equipmentLab, List<String> equipmentSerieList) {
        this.id = equipmentLab.getId();
        this.labId = equipmentLab.getLabId();
        this.equipmentId = equipmentLab.getEquipmentId();
        this.equipmentSeries = equipmentSerieList.toString();
        this.equipmentSerieList = equipmentSerieList;
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

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getEquipmentSeries() {
        return equipmentSeries;
    }

    public void setEquipmentSeries(String equipmentSeries) {
        this.equipmentSeries = equipmentSeries;
    }

    public List<String> getEquipmentSerieList() {
        return equipmentSerieList;
    }

    public void setEquipmentSerieList(List<String> equipmentSerieList) {
        this.equipmentSerieList = equipmentSerieList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    public List<String> getUsingdates() {
        return usingdates;
    }

    public void setUsingdates(List<String> usingdates) {
        this.usingdates = usingdates;
    }

    @Override
    public String toString() {
        return "EquipmentLabDTO{" +
                "id=" + id +
                ", labId=" + labId +
                ", equipmentId=" + equipmentId +
                ", equipment=" + equipment +
                ", name='" + name + '\'' +
                ", equipmentSeries='" + equipmentSeries + '\'' +
                ", equipmentSerieList=" + equipmentSerieList +
                ", levels=" + levels +
                ", usingdates=" + usingdates +
                '}';
    }
}

