package LabManagement.service.equipmentLab;

import LabManagement.entity.EquipmentLab;

import java.util.List;

public interface EquipmentLabService {
    EquipmentLab saveEquipmentLab(EquipmentLab equipmentLab);
    void deleteEquipmentLab(int id);
    List<EquipmentLab> getAllEquipmentLabs();
    EquipmentLab getEquipmentLabById(int id);
    List<EquipmentLab> findAllByLabId(int id);
    EquipmentLab findByLabIdAndEquipmentId(int lab_id, int equi_id);
}
