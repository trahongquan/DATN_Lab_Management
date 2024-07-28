package LabManagement.dao;

import LabManagement.entity.Equipment;
import LabManagement.entity.RecallEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecallEquipmentRepository extends JpaRepository<RecallEquipment, Integer> {
        Optional<RecallEquipment> findByEquipID(int id);
}

