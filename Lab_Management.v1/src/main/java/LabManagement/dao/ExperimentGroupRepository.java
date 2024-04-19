package LabManagement.dao;

import LabManagement.entity.ExperimentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentGroupRepository extends JpaRepository<ExperimentGroup, Integer> {
    // Custom query methods can be defined here if needed
}
