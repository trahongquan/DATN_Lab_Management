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

    @Column(name = "experiment_group_id", nullable = false)
    private int experimentGroupId;
        public ExperimentType() {
    }

    public ExperimentType(String typeName, int experimentGroupId) {
        this.typeName = typeName;
        this.experimentGroupId = experimentGroupId;
    }

    @Override
    public String toString() {
        return "ExperimentType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", experimentGroupId=" + experimentGroupId +
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

    public int getExperimentGroupId() {
        return experimentGroupId;
    }

    public void setExperimentGroupId(int experimentGroupId) {
        this.experimentGroupId = experimentGroupId;
    }

}