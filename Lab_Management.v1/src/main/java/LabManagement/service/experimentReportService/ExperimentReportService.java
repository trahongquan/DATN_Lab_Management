package LabManagement.service.experimentReportService;

import LabManagement.entity.ExperimentReport;
import LabManagement.entity.ExperimentType;

import java.util.List;

public interface ExperimentReportService {
    ExperimentReport saveExperimentReport(ExperimentReport experimentReport);
    void deleteExperimentReport(int id);
    List<ExperimentReport> getAllExperimentReports();
    ExperimentReport getExperimentReportById(int id);
}