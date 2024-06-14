package LabManagement.dao;

import LabManagement.entity.EquipmentLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentLabRepository extends JpaRepository<EquipmentLab, Integer> {
    List<EquipmentLab> findAllByLabId(int id);
    EquipmentLab findByLabIdAndEquipmentId(int lab_id, int equi_id);
    List<EquipmentLab> findAllByLabIdAndEquipmentId(int lab_id, int equi_id);
    List<EquipmentLab> findAllByEquipmentId(int equi_id);
}
