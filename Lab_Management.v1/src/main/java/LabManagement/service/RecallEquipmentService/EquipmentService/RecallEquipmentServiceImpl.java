package LabManagement.service.RecallEquipmentService.EquipmentService;

import LabManagement.dao.RecallEquipmentRepository;
import LabManagement.entity.RecallEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecallEquipmentServiceImpl implements RecallEquipmentService {

    @Autowired
    private RecallEquipmentRepository recallEquipmentRepository;

    @Override
    public RecallEquipment findByRecallEquipmentId(int equipmentId) {
        return recallEquipmentRepository.findById(equipmentId).orElse(null);
    }

    @Override
    public RecallEquipment findByEquipmentId(int equipmentId){
        Optional<RecallEquipment> optional = recallEquipmentRepository.findByEquipID(equipmentId);

        if(optional.isPresent()) {
            return optional.get();
        } else {
            return new RecallEquipment(-1);
        }
    }

    @Override
    public List<RecallEquipment> getAllRecallEquipment() {
        return recallEquipmentRepository.findAll();
    }

    @Override
    public RecallEquipment createRecallEquipment(RecallEquipment equipment) {
        return recallEquipmentRepository.save(equipment);
    }

    @Override
    public void updateRecallEquipment(RecallEquipment equipment) {
        recallEquipmentRepository.save(equipment);
    }

    @Override
    public void deleteEquipment(int equipmentId) {
        recallEquipmentRepository.deleteById(equipmentId);
    }
}

