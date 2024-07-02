package LabManagement.ClassSuport;

import LabManagement.dto.EquipmentLabDTO;
import LabManagement.dto.LabDTO;
import LabManagement.entity.InventoryEquipment;
import LabManagement.entity.InventoryLab;

import java.util.ArrayList;
import java.util.List;

public class InventoryResult {
    private List<InventoryLab> inventoryLab_DataForYear;
    private List<InventoryEquipment> inventoryEquipment_DataForYear;
    private List<EquipmentLabDTO> equipmentLabDTOs;
    private List<InventoryCompare> inventoryCompares;
    private LabDTO labDTO;

    public InventoryResult() {
        this.inventoryLab_DataForYear = new ArrayList<>();
        this.inventoryEquipment_DataForYear = new ArrayList<>();
        this.equipmentLabDTOs = new ArrayList<>();
        this.inventoryCompares = new ArrayList<>();
        this.labDTO = new LabDTO();
    }

    public InventoryResult(List<InventoryLab> inventoryLab_DataForYear,
                           List<EquipmentLabDTO> equipmentLabDTOs,
                           List<InventoryCompare> inventoryCompares,
                           LabDTO labDTO) {
        this.inventoryLab_DataForYear = inventoryLab_DataForYear;
        this.equipmentLabDTOs = equipmentLabDTOs;
        this.inventoryCompares = inventoryCompares;
        this.labDTO = labDTO;
    }
    public InventoryResult(List<InventoryEquipment> inventoryEquipment_DataForYear,
                           List<EquipmentLabDTO> equipmentLabDTOs,
                           List<InventoryCompare> inventoryCompares) {
        this.inventoryEquipment_DataForYear = inventoryEquipment_DataForYear;
        this.equipmentLabDTOs = equipmentLabDTOs;
        this.inventoryCompares = inventoryCompares;
    }

    public List<InventoryLab> getInventoryLab_DataForYear() {
        return inventoryLab_DataForYear;
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

    public void setInventoryLab_DataForYear(List<InventoryLab> inventoryLab_DataForYear) {
        this.inventoryLab_DataForYear = inventoryLab_DataForYear;
    }

    public void setEquipmentLabDTOs(List<EquipmentLabDTO> equipmentLabDTOs) {
        this.equipmentLabDTOs = equipmentLabDTOs;
    }

    public void setInventoryCompares(List<InventoryCompare> inventoryCompares) {
        this.inventoryCompares = inventoryCompares;
    }

    public List<InventoryEquipment> getInventoryEquipment_DataForYear() {
        return inventoryEquipment_DataForYear;
    }

    public void setInventoryEquipment_DataForYear(List<InventoryEquipment> inventoryEquipment_DataForYear) {
        this.inventoryEquipment_DataForYear = inventoryEquipment_DataForYear;
    }
}
