package LabManagement.dto;

import LabManagement.entity.ExperimentGroup;
import LabManagement.entity.ExperimentReport;
import LabManagement.entity.ExperimentType;
import LabManagement.entity.Score;

public class ScoreDTO {
    private int id;
    private int experimentGroupId;
    private int experimentTypeId;
    private int experimentReportId;
    private Double score;

    private ExperimentGroup experimentGroup;
    private ExperimentType experimentType;
    private ExperimentReport experimentReport;

    public ScoreDTO() {
    }

    public ScoreDTO(Score scoreEntity, ExperimentGroup experimentGroup, ExperimentType experimentType, ExperimentReport experimentReport) {
        this.id = scoreEntity.getId();
        this.experimentGroupId = scoreEntity.getExperimentGroupId();
        this.experimentTypeId = scoreEntity.getExperimentTypeId();
        this.experimentReportId = scoreEntity.getExperimentReportId();
        this.score = scoreEntity.getScore();
        this.experimentGroup = experimentGroup;
        this.experimentType = experimentType;
        this.experimentReport = experimentReport;
    }

    @Override
    public String toString() {
        return "ScoreDTO{" +
                "id=" + id +
                ", experimentGroupId=" + experimentGroupId +
                ", experimentTypeId=" + experimentTypeId +
                ", experimentReportId=" + experimentReportId +
                ", score=" + score +
                ", experimentGroup=" + experimentGroup +
                ", experimentType=" + experimentType +
                ", experimentReport=" + experimentReport +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExperimentGroupId() {
        return experimentGroupId;
    }

    public void setExperimentGroupId(int experimentGroupId) {
        this.experimentGroupId = experimentGroupId;
    }

    public int getExperimentTypeId() {
        return experimentTypeId;
    }

    public void setExperimentTypeId(int experimentTypeId) {
        this.experimentTypeId = experimentTypeId;
    }

    public int getExperimentReportId() {
        return experimentReportId;
    }

    public void setExperimentReportId(int experimentReportId) {
        this.experimentReportId = experimentReportId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ExperimentGroup getExperimentGroup() {
        return experimentGroup;
    }

    public void setExperimentGroup(ExperimentGroup experimentGroup) {
        this.experimentGroup = experimentGroup;
    }

    public ExperimentType getExperimentType() {
        return experimentType;
    }

    public void setExperimentType(ExperimentType experimentType) {
        this.experimentType = experimentType;
    }

    public ExperimentReport getExperimentReport() {
        return experimentReport;
    }

    public void setExperimentReport(ExperimentReport experimentReport) {
        this.experimentReport = experimentReport;
    }
}
