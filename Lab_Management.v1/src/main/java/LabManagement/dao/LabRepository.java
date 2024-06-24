package LabManagement.dao;

import LabManagement.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<Lab, Integer> {
    List<Lab> findAllByLabNameContainingOrLocationContainingOrAndManagingUnitContaining(String st1, String st2, String st3);
}
