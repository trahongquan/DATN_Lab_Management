package LabManagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "experiment_type")
public class ExperimentType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiment_group_id", nullable = false)
    private ExperimentGroup experimentGroup;

    @Column(name = "scores", nullable = false)
    private double scores;

    public ExperimentType() {
    }

    public ExperimentType(String typeName, ExperimentGroup experimentGroup, double scores) {
        this.typeName = typeName;
        this.experimentGroup = experimentGroup;
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "ExperimentType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", experimentGroup=" + experimentGroup +
                ", scores=" + scores +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public ExperimentGroup getExperimentGroup() {
        return experimentGroup;
    }

    public void setExperimentGroup(ExperimentGroup experimentGroup) {
        this.experimentGroup = experimentGroup;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }
}