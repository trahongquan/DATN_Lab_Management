package LabManagement.ClassSuport;

import LabManagement.dto.EquipmentLabDTO;
import LabManagement.dto.LabDTO;
import LabManagement.entity.InventoryLab;

import java.util.ArrayList;
import java.util.List;

public class InventoryResult {
    private List<InventoryLab> equipmentLabDtosInventoryForYear;
    private List<EquipmentLabDTO> equipmentLabDTOs;
    private List<InventoryCompare> inventoryCompares;
    private LabDTO labDTO;

    public InventoryResult() {
        this.equipmentLabDtosInventoryForYear = new ArrayList<>();
        this.equipmentLabDTOs = new ArrayList<>();
        this.inventoryCompares = new ArrayList<>();
        this.labDTO = new LabDTO();
    }

    public InventoryResult(List<InventoryLab> equipmentLabDtosInventoryForYear,
                           List<EquipmentLabDTO> equipmentLabDTOs,
                           List<InventoryCompare> inventoryCompares,
                           LabDTO labDTO) {
        this.equipmentLabDtosInventoryForYear = equipmentLabDtosInventoryForYear;
        this.equipmentLabDTOs = equipmentLabDTOs;
        this.inventoryCompares = inventoryCompares;
        this.labDTO = labDTO;
    }

    public List<InventoryLab> getEquipmentLabDtosInventoryForYear() {
        return equipmentLabDtosInventoryForYear;
    }

    public List<EquipmentLabDTO> getEquipmentLabDTOs() {
        return equipmentLabDTOs;
    }

    public List<InventoryCompare> getInventoryCompares() {
        return inventoryCompares;
    }

    public LabDTO getLabDTO() {
        return labDTO;
    }

    public void setLabDTO(LabDTO labDTO) {
        this.labDTO = labDTO;
    }

    public void setEquipmentLabDtosInventoryForYear(List<InventoryLab> equipmentLabDtosInventoryForYear) {
        this.equipmentLabDtosInventoryForYear = equipmentLabDtosInventoryForYear;
    }

    public void setEquipmentLabDTOs(List<EquipmentLabDTO> equipmentLabDTOs) {
        this.equipmentLabDTOs = equipmentLabDTOs;
    }

    public void setInventoryCompares(List<InventoryCompare> inventoryCompares) {
        this.inventoryCompares = inventoryCompares;
    }
}
