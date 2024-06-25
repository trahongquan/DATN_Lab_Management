package LabManagement.service.EquipmentLabDtoInventoryService;

import LabManagement.entity.EquipmentLabDtoInventory;

import java.util.List;

public interface EquipmentLabDtoInventoryService {
    EquipmentLabDtoInventory saveEquipmentLab(EquipmentLabDtoInventory equipmentLabDto);
    EquipmentLabDtoInventory getEquipmentLabById(int id);
    List<EquipmentLabDtoInventory> getAllEquipmentLabs();
    void deleteEquipmentLab(int id);
    EquipmentLabDtoInventory findByLabIdAndAndYear(int labId, int year);
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
}
