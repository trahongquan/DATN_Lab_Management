package LabManagement.entity;

import LabManagement.ClassSuport.ToList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "experiment_report")
public class ExperimentReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "experiment_group_id", nullable = false)
    private int experimentGroupId;

    @Column(name = "experiment_type_id", nullable = false)
    private String experimentTypeId;

    public ExperimentReport() {
    }

    public ExperimentReport(String reportType, int experimentGroupId, String experimentTypeId) {
        this.reportType = reportType;
        this.experimentGroupId = experimentGroupId;
        this.experimentTypeId = experimentTypeId;
    }

    @Override
    public String toString() {
        return "ExperimentReport{" +
                "id=" + id +
                ", reportType='" + reportType + '\'' +
                ", experimentGroupId=" + experimentGroupId +
                ", experimentTypeId=" + experimentTypeId +
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

    public List<Integer> getExpTypeIdList(){
        return new ToList().StringToListINT(this.getExperimentTypeId());
    }

    public void setExpTypeIdList(List<Integer> expTypeIdList){
        this.experimentTypeId = expTypeIdList.toString();
    }
}