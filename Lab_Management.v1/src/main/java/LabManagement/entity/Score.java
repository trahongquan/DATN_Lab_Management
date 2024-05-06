package LabManagement.entity;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "experoment_group_id")
    private int experimentGroupId;

    @Column(name = "experoment_type_id")
    private int experimentTypeId;

    @Column(name = "experoment_report_id")
    private int experimentReportId;

    @Column(name = "score")
    private Double score;

    public Score() {
    }

    public Score(int experimentGroupId, int experimentTypeId, int experimentReportId, Double score) {
        this.experimentGroupId = experimentGroupId;
        this.experimentTypeId = experimentTypeId;
        this.experimentReportId = experimentReportId;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", experimentGroupId=" + experimentGroupId +
                ", experimentTypeId=" + experimentTypeId +
                ", experimentReportId=" + experimentReportId +
                ", score=" + score +
                '}';
    }

    // Getters and setters

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
}
