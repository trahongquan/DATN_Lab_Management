package LabManagement.service.scoreService;

import LabManagement.entity.Score;

import java.util.List;

public interface ScoreService {
    Score saveScore(Score score);
    Score getScoreById(int id);
    void delScoreById(int id);
    List<Score>  getAllScore();
    List<Score> FindAllByExperimentGroupId(int id);
    List<Score> FindAllByExperimentTypeId(int id);
    List<Score> FindAllByExperimentReportId(int id);
    Score FindByExperimentGroupIdAndExperimentTypeIdAndExperimentReportId(int experimentGroupId, int experimentTypeId, int experimentReportId);
}