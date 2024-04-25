package LabManagement.service.experimentReportService;

import LabManagement.dao.ExperimentReportRepository;
import LabManagement.dao.ExperimentTypeRepository;
import LabManagement.entity.ExperimentReport;
import LabManagement.entity.ExperimentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExperimentReportServiceImpl implements ExperimentReportService {

    private final ExperimentReportRepository experimentReportRepository;

    public ExperimentReportServiceImpl(ExperimentReportRepository experimentReportRepository) {
        this.experimentReportRepository = experimentReportRepository;
    }

    @Override
    public ExperimentReport saveExperimentReport(ExperimentReport experimentReport) {
        return experimentReportRepository.save(experimentReport);
    }

    @Override
    public void deleteExperimentReport(int id) {
        experimentReportRepository.deleteById(id);
    }

    @Override
    public List<ExperimentReport> getAllExperimentReports() {
        return experimentReportRepository.findAll();
    }

    @Override
    public ExperimentReport getExperimentReportById(int id) {
        return experimentReportRepository.findById(id).orElse(null);
    }
}
