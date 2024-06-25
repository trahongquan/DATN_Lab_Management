package LabManagement.service.EquipmentLabDtoInventoryService;

import LabManagement.dao.EquipmentLabDtoInventoryRepository;
import LabManagement.entity.EquipmentLabDtoInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentLabDtoInventoryServiceImpl implements EquipmentLabDtoInventoryService {

    private EquipmentLabDtoInventoryRepository equipmentLabDtoRepository;

    @Autowired
    public EquipmentLabDtoInventoryServiceImpl(EquipmentLabDtoInventoryRepository equipmentLabDtoRepository) {
        this.equipmentLabDtoRepository = equipmentLabDtoRepository;
    }

    @Override
    public EquipmentLabDtoInventory saveEquipmentLab(EquipmentLabDtoInventory equipmentLabDto) {
        return equipmentLabDtoRepository.save(equipmentLabDto);
    }

    @Override
    public EquipmentLabDtoInventory getEquipmentLabById(int id) {
        return equipmentLabDtoRepository.findById(id).orElse(null);
    }

    @Override
    public List<EquipmentLabDtoInventory> getAllEquipmentLabs() {
        return equipmentLabDtoRepository.findAll();
    }

    @Override
    public void deleteEquipmentLab(int id) {
        equipmentLabDtoRepository.deleteById(id);
    }

    @Override
    public EquipmentLabDtoInventory findByLabIdAndAndYear(int labId, int year) {
        Optional<EquipmentLabDtoInventory> optional = equipmentLabDtoRepository.findByLabIdAndAndYear(labId, year);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            EquipmentLabDtoInventory equipmentLabDtoInventory = new EquipmentLabDtoInventory();
            equipmentLabDtoInventory.setId(0);
            return equipmentLabDtoInventory;
        }
    }
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
}
