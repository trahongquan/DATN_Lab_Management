package LabManagement.service.RecallEquipmentService.EquipmentService;

import LabManagement.entity.Equipment;
import LabManagement.entity.RecallEquipment;

import java.util.List;

public interface RecallEquipmentService {

    RecallEquipment findByRecallEquipmentId(int equipmentId);

    RecallEquipment findByEquipmentId(int equipmentId);

    List<RecallEquipment> getAllRecallEquipment();

    RecallEquipment createRecallEquipment(RecallEquipment equipment);

    void updateRecallEquipment(RecallEquipment equipment);

    void deleteEquipment(int equipmentId);
}
