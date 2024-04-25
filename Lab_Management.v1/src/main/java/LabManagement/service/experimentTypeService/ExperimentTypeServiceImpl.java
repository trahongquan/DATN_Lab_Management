package LabManagement.service.experimentTypeService;

import LabManagement.dao.ExperimentTypeRepository;
import LabManagement.entity.ExperimentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExperimentTypeServiceImpl implements ExperimentTypeService {

    private final ExperimentTypeRepository experimentTypeRepository;

    public ExperimentTypeServiceImpl(ExperimentTypeRepository experimentTypeRepository) {
        this.experimentTypeRepository = experimentTypeRepository;
    }

    @Override
    public ExperimentType saveExperimentType(ExperimentType experimentType) {
        return experimentTypeRepository.save(experimentType);
    }

    @Override
    public void deleteExperimentType(int id) {
        experimentTypeRepository.deleteById(id);
    }

    @Override
    public List<ExperimentType> getAllExperimentTypes() {
        return experimentTypeRepository.findAll();
    }

    @Override
    public List<ExperimentType> findAllByExperimentGroupId(int id) {
        return experimentTypeRepository.findAllByExperimentGroupId(id);
    }

    @Override
    public ExperimentType getExperimentTypeById(int id) {
        return experimentTypeRepository.findById(id).orElse(null);
    }
}
