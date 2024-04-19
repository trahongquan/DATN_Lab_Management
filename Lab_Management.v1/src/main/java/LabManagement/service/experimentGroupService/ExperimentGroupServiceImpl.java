package LabManagement.service.experimentGroupService;

import LabManagement.dao.ExperimentGroupRepository;
import LabManagement.entity.ExperimentGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExperimentGroupServiceImpl implements ExperimentGroupService {

    private final ExperimentGroupRepository experimentGroupRepository;

    public ExperimentGroupServiceImpl(ExperimentGroupRepository experimentGroupRepository) {
        this.experimentGroupRepository = experimentGroupRepository;
    }

    @Override
    public ExperimentGroup saveExperimentGroup(ExperimentGroup experimentGroup) {
        return experimentGroupRepository.save(experimentGroup);
    }

    @Override
    public void deleteExperimentGroup(int id) {
        experimentGroupRepository.deleteById(id);
    }

    @Override
    public List<ExperimentGroup> getAllExperimentGroups() {
        return experimentGroupRepository.findAll();
    }

    @Override
    public ExperimentGroup getExperimentGroupById(int id) {
        return experimentGroupRepository.findById(id).orElse(null);
    }
}
