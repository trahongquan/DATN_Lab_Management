package LabManagement.dao;

import LabManagement.entity.InventoryLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryLabRepository extends JpaRepository<InventoryLab, Integer> {
    // Các phương thức truy vấn tùy chỉnh (nếu cần)
    Optional<InventoryLab> findByLabIdAndAndYear(int labId, int year);
}
