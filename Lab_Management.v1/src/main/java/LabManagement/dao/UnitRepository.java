package LabManagement.dao;

import LabManagement.entity.Authority;
import LabManagement.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    // Custom query methods, if needed
}

