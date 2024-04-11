package LabManagement.service.EquipmentService;

import LabManagement.entity.Equipment;

import java.util.List;

public interface EquipmentService {

    Equipment findByEquipmentId(int equipmentId);

    List<Equipment> getAllEquipment();

    Equipment createEquipment(Equipment equipment);

    void updateEquipment(Equipment equipment);

    void deleteEquipment(int equipmentId);
}
