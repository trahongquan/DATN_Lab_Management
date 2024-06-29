package LabManagement.service.InventoryLabService;

import LabManagement.dao.InventoryLabRepository;
import LabManagement.entity.InventoryLab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryLabServiceImpl implements InventoryLabService {

    private InventoryLabRepository equipmentLabDtoRepository;

    @Autowired
    public InventoryLabServiceImpl(InventoryLabRepository equipmentLabDtoRepository) {
        this.equipmentLabDtoRepository = equipmentLabDtoRepository;
    }

    @Override
    public InventoryLab saveEquipmentLab(InventoryLab equipmentLabDto) {
        return equipmentLabDtoRepository.save(equipmentLabDto);
    }

    @Override
    public InventoryLab getEquipmentLabById(int id) {
        return equipmentLabDtoRepository.findById(id).orElse(null);
    }

    @Override
    public List<InventoryLab> getAllEquipmentLabs() {
        return equipmentLabDtoRepository.findAll();
    }

    @Override
    public void deleteEquipmentLab(int id) {
        equipmentLabDtoRepository.deleteById(id);
    }

    @Override
    public InventoryLab findByLabIdAndAndYear(int labId, int year) {
        Optional<InventoryLab> optional = equipmentLabDtoRepository.findByLabIdAndAndYear(labId, year);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            InventoryLab equipmentLabDtoInventory = new InventoryLab();
            equipmentLabDtoInventory.setId(0);
            return equipmentLabDtoInventory;
        }
    }
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
}
