package LabManagement.service.InventoryEquipmentService;

import LabManagement.entity.InventoryEquipment;

import java.util.List;

public interface InventoryEquipmentService {
    List<InventoryEquipment> getAllInventoryEquipment();
    InventoryEquipment getInventoryEquipmentById(int id);
    void saveInventoryEquipment(InventoryEquipment inventoryEquipment);
    void deleteInventoryEquipmentById(int id);
}
