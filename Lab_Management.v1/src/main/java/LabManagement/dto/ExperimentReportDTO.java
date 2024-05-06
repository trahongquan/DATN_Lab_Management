package LabManagement.dto;

import LabManagement.ClassSuport.ToList;
import LabManagement.entity.ExperimentGroup;
import LabManagement.entity.ExperimentReport;
import LabManagement.entity.ExperimentType;

import java.util.List;

public class ExperimentReportDTO {

    private int id;
    private String reportType;
    private int experimentGroupId;
    private String experimentTypeId;

    private ExperimentGroup experimentGroup;
    private List<ExperimentType> experimentTypes;

    public ExperimentReportDTO(ExperimentReport experimentReport, ExperimentGroup experimentGroup, List<ExperimentType> experimentTypes) {
        this.id = experimentReport.getId();
        this.reportType = experimentReport.getReportType();
        this.experimentGroupId = experimentReport.getExperimentGroupId();
        this.experimentTypeId = experimentReport.getExperimentTypeId();
        this.experimentGroup = experimentGroup;
        this.experimentTypes = experimentTypes;
    }

    @Override
    public String toString() {
        return "ExperimentReportDTO{" +
                "id=" + id +
                ", reportType='" + reportType + '\'' +
                ", experimentGroupId=" + experimentGroupId +
                ", experimentTypeId='" + experimentTypeId + '\'' +
                ", experimentGroup=" + experimentGroup +
                ", experimentTypes=" + experimentTypes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getExperimentGroupId() {
        return experimentGroupId;
    }

    public void setExperimentGroupId(int experimentGroupId) {
        this.experimentGroupId = experimentGroupId;
    }

    public String getExperimentTypeId() {
        return experimentTypeId;
    }

    public void setExperimentTypeId(String experimentTypeId) {
        this.experimentTypeId = experimentTypeId;
    }

    public ExperimentGroup getExperimentGroup() {
        return experimentGroup;
    }

    public void setExperimentGroup(ExperimentGroup experimentGroup) {
        this.experimentGroup = experimentGroup;
    }

    public List<ExperimentType> getExperimentTypes() {
        return experimentTypes;
    }

    public void setExperimentType(List<ExperimentType> experimentTypes) {
        this.experimentTypes = experimentTypes;
    }

    public List<Integer> getExpTypeIdList(){
        return new ToList().StringToListINT(this.getExperimentTypeId());
    }

    public void setExpTypeIdList(List<Integer> expTypeIdList){
        this.experimentTypeId = expTypeIdList.toString();
    }
}

