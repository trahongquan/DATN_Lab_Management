package LabManagement.dao;

import LabManagement.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    // Custom query methods can be defined here if needed
    List<Content> findAllByReservationistId(int id);
}
