package LabManagement.ClassSuport.EquidLevel;

import LabManagement.entity.Equipment;

import java.util.ArrayList;
import java.util.List;

public class AllEquipment_ReadOnly {
    Equipment equipment;
    List<String> LabNames = new ArrayList<>();
    List<Integer> LabId = new ArrayList<>();

    public AllEquipment_ReadOnly(Equipment equipment, List<String> labNames, List<Integer> labId) {
        this.equipment = equipment;
        LabNames = labNames;
        LabId = labId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public List<String> getLabNames() {
        return LabNames;
    }

    public void setLabNames(List<String> labNames) {
        LabNames = labNames;
    }

    public List<Integer> getLabId() {
        return LabId;
    }

    public void setLabId(List<Integer> labId) {
        LabId = labId;
    }
}
