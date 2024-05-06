package LabManagement.dto;

import LabManagement.entity.ExperimentGroup;
import LabManagement.entity.ExperimentType;

public class ExperimentTypeDTO {

    private int id;

    private String typeName;

    private int experimentGroupId;

    private ExperimentGroup experimentGroup;

    public ExperimentTypeDTO(ExperimentType experimentType, ExperimentGroup experimentGroup) {
        this.id = experimentType.getId();
        this.typeName = experimentType.getTypeName();
        this.experimentGroupId = experimentType.getExperimentGroupId();
        this.experimentGroup = experimentGroup;
    }

    @Override
    public String toString() {
        return "ExperimentTypeDTO{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", experimentGroupId=" + experimentGroupId +
                ", experimentGroup=" + experimentGroup +
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

    public ExperimentGroup getExperimentGroup() {
        return experimentGroup;
    }

    public void setExperimentGroup(ExperimentGroup experimentGroup) {
        this.experimentGroup = experimentGroup;
    }
}
