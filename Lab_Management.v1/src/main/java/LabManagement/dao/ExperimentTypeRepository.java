package LabManagement.dao;

import LabManagement.entity.ExperimentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentTypeRepository extends JpaRepository<ExperimentType, Integer> {
    // Custom query methods can be defined here if needed
}
