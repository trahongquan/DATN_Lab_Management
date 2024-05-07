package LabManagement.service.scoreService;

import LabManagement.dao.ScoreRepository;
import LabManagement.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public Score getScoreById(int id) {
        return scoreRepository.findById(id).orElse(null);
    }
    @Override
    public void delScoreById(int id){
        scoreRepository.deleteById(id);
    }
    @Override
    public List<Score>  getAllScore() {
        return scoreRepository.findAll();
    }
    @Override
    public List<Score> FindAllByExperimentGroupId(int id){
        List<Score> scores = scoreRepository.findAllByExperimentGroupId(id);
        return scores;
    };
    @Override
    public List<Score> FindAllByExperimentTypeId(int id){
        return scoreRepository.findAllByExperimentTypeId(id);
    };
    @Override
    public List<Score> FindAllByExperimentReportId(int id){
        return scoreRepository.findAllByExperimentReportId(id);
    };

    @Override
    public Score FindByExperimentGroupIdAndExperimentTypeIdAndExperimentReportId(int experimentGroupId, int experimentTypeId, int experimentReportId){
        return scoreRepository.findByExperimentGroupIdAndExperimentTypeIdAndExperimentReportId(experimentGroupId, experimentTypeId, experimentReportId);
    }
}
