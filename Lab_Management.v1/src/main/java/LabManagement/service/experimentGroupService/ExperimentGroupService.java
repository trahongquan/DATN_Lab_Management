package LabManagement.service.experimentGroupService;

import LabManagement.entity.ExperimentGroup;

import java.util.List;

public interface ExperimentGroupService {
    ExperimentGroup saveExperimentGroup(ExperimentGroup experimentGroup);
    void deleteExperimentGroup(int id);
    List<ExperimentGroup> getAllExperimentGroups();
    ExperimentGroup getExperimentGroupById(int id);
}
