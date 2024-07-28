package LabManagement.dtoExport;

import LabManagement.ClassSuport.ToList;
import LabManagement.entity.Equipment;
import LabManagement.entity.RecallEquipment;
import LabManagement.service.RecallEquipmentService.EquipmentService.RecallEquipmentService;

import java.util.List;

public class RecallEquipmentDTOExport {
    private int id;
    private String name;
    private String origin;
    private String unit;
    private String seri;
    private String level;
    private String recallDate;
    private String labNameRecall;
    private String note;

    public RecallEquipmentDTOExport() {
    }

    public RecallEquipmentDTOExport(Equipment equipment, RecallEquipment recallEquipment, int position) {
        this.id = recallEquipment.getId();
        this.name = equipment.getName();
        this.origin = equipment.getOrigin();
        this.unit = equipment.getUnit();
        this.seri = recallEquipment.getSeriList().get(position);
        this.level = recallEquipment.getLevelList().get(position);
        this.recallDate = recallEquipment.getRecallDateList().get(position);
        this.note = recallEquipment.getNoteList().get(position);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSeries() {
        return seri;
    }

    public void setSeries(String series) {
        this.seri = series;
    }

    public String getLevels() {
        return level;
    }

    public void setLevels(String levels) {
        this.level = levels;
    }

    public String getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(String recallDate) {
        this.recallDate = recallDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
