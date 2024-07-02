package LabManagement.dao;

import LabManagement.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    // Additional methods if needed
    List<Lesson> findAllByLabId(int id);
}
