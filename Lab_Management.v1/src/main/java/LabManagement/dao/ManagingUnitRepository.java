package LabManagement.dao;

import LabManagement.entity.ManagingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagingUnitRepository extends JpaRepository<ManagingUnit, Integer> {
    ManagingUnit findByDepartmentName(String s);
}
