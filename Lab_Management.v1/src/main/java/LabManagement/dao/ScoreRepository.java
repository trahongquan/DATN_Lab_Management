package LabManagement.dao;

import LabManagement.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findAllByExperimentGroupId(int id);
    List<Score> findAllByExperimentTypeId(int id);
    List<Score> findAllByExperimentReportId(int id);
    Score findByExperimentGroupIdAndExperimentTypeIdAndExperimentReportId(int experimentGroupId, int experimentTypeId, int experimentReportId);
}