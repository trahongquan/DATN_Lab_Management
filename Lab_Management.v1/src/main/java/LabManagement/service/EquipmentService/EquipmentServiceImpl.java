package LabManagement.service.EquipmentService;

import LabManagement.entity.Equipment;
import LabManagement.dao.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment findByEquipmentId(int equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> getAllEquipmentOnLine() {
        return equipmentRepository.findAll().stream().filter(equipment -> equipment.getIsDeleted()==0).collect(Collectors.toList());
    }

    @Override
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(int equipmentId) {
        Equipment equipment = findByEquipmentId(equipmentId);
        equipment.setIsDeleted(1);
        updateEquipment(equipment);
    }
}

