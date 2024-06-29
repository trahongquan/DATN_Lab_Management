package LabManagement.service.InventoryLabService;

import LabManagement.entity.InventoryLab;

import java.util.List;

public interface InventoryLabService {
    InventoryLab saveEquipmentLab(InventoryLab equipmentLabDto);
    InventoryLab getEquipmentLabById(int id);
    List<InventoryLab> getAllEquipmentLabs();
    void deleteEquipmentLab(int id);
    InventoryLab findByLabIdAndAndYear(int labId, int year);
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
}
