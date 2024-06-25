package LabManagement.dao;

import LabManagement.entity.EquipmentLabDtoInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentLabDtoInventoryRepository extends JpaRepository<EquipmentLabDtoInventory, Integer> {
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
    Optional<EquipmentLabDtoInventory> findByLabIdAndAndYear(int labId, int year);
}
