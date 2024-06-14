package LabManagement.service.equipmentLab;

import LabManagement.dao.EquipmentLabRepository;
import LabManagement.entity.EquipmentLab;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentLabServiceImpl implements EquipmentLabService {

    private final EquipmentLabRepository equipmentLabRepository;

    public EquipmentLabServiceImpl(EquipmentLabRepository equipmentLabRepository) {
        this.equipmentLabRepository = equipmentLabRepository;
    }

    @Override
    public EquipmentLab saveEquipmentLab(EquipmentLab equipmentLab) {
        return equipmentLabRepository.save(equipmentLab);
    }

    @Override
    public void deleteEquipmentLab(int id) {
        equipmentLabRepository.deleteById(id);
    }

    @Override
    public List<EquipmentLab> getAllEquipmentLabs() {
        return equipmentLabRepository.findAll();
    }

    @Override
    public EquipmentLab getEquipmentLabById(int id) {
        return equipmentLabRepository.findById(id).orElse(null);
    }

    @Override
    public List<EquipmentLab> findAllByLabId(int id){
        return !equipmentLabRepository.findAllByLabId(id).isEmpty() ? equipmentLabRepository.findAllByLabId(id) : new ArrayList<>();
    }

    @Override
    public EquipmentLab findByLabIdAndEquipmentId(int lab_id, int equi_id) {
        if (equipmentLabRepository.findByLabIdAndEquipmentId(lab_id, equi_id) != null) {
            return equipmentLabRepository.findByLabIdAndEquipmentId(lab_id, equi_id);
        } else {
//            throw new IllegalArgumentException("EquipmentLab not found for labId: " + lab_id + " and equipmentId: " + equi_id);
            EquipmentLab equipmentLab = new EquipmentLab();
            equipmentLab.setId(0);
            return equipmentLab;
        }
    }
    @Override
    public List<EquipmentLab> findAllByLabIdAndEquipmentId(int lab_id, int equi_id){
        if (equipmentLabRepository.findAllByLabIdAndEquipmentId(lab_id, equi_id) != null) {
            return equipmentLabRepository.findAllByLabIdAndEquipmentId(lab_id, equi_id);
        } else {
            return new ArrayList<>();
        }
    }
    @Override
    public List<EquipmentLab> findAllByEquipmentId(int equi_id){
        if (equipmentLabRepository.findAllByEquipmentId(equi_id) != null) {
            return equipmentLabRepository.findAllByEquipmentId(equi_id);
        } else {
            return new ArrayList<>();
        }
    }

}
