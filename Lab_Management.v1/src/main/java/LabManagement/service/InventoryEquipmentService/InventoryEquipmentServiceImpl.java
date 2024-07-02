package LabManagement.service.InventoryEquipmentService;

import LabManagement.dao.InventoryEquipmentRepository;
import LabManagement.entity.InventoryEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryEquipmentServiceImpl implements InventoryEquipmentService {

    private final InventoryEquipmentRepository inventoryEquipmentRepository;

    @Autowired
    public InventoryEquipmentServiceImpl(InventoryEquipmentRepository inventoryEquipmentRepository) {
        this.inventoryEquipmentRepository = inventoryEquipmentRepository;
    }

    @Override
    public List<InventoryEquipment> getAllInventoryEquipment() {
        return inventoryEquipmentRepository.findAll();
    }

    @Override
    public InventoryEquipment findByYear(int year){
//        Optional<InventoryEquipment> optional = (Optional<InventoryEquipment>) inventoryEquipmentRepository.findByYear(year);
        try {
            return inventoryEquipmentRepository.findByYear(year);
        } catch (Exception e) {
            InventoryEquipment inventoryEquipment = new InventoryEquipment();
            inventoryEquipment.setId(0);
            return inventoryEquipment;
        }
    }

    @Override
    public InventoryEquipment getInventoryEquipmentById(int id) {
        Optional<InventoryEquipment> optionalInventoryEquipment = inventoryEquipmentRepository.findById(id);
        return optionalInventoryEquipment.orElse(null);
    }

    @Override
    public void saveInventoryEquipment(InventoryEquipment inventoryEquipment) {
        inventoryEquipmentRepository.save(inventoryEquipment);
    }

    @Override
    public void deleteInventoryEquipmentById(int id) {
        inventoryEquipmentRepository.deleteById(id);
    }
}