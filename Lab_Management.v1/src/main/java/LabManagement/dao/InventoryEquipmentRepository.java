package LabManagement.dao;

import LabManagement.entity.InventoryEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryEquipmentRepository extends JpaRepository<InventoryEquipment, Integer> {
    // Custom query methods or additional operations can be defined here
    InventoryEquipment findByYear(int year);
}
