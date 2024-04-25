package LabManagement.service.experimentTypeService;

import LabManagement.entity.ExperimentType;

import java.util.List;

public interface ExperimentTypeService {
    ExperimentType saveExperimentType(ExperimentType experimentType);
    void deleteExperimentType(int id);
    List<ExperimentType> getAllExperimentTypes();
    ExperimentType getExperimentTypeById(int id);
    List<ExperimentType> findAllByExperimentGroupId(int id);
}