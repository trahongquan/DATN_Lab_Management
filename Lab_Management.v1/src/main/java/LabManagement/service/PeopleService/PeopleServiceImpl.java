package LabManagement.service.PeopleService;

import LabManagement.entity.People;
import LabManagement.dao.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public People findByPeopleId(int peopleId) {
        return peopleRepository.findById(peopleId).orElse(null);
    }

    @Override
    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    @Override
    public People createPeople(People people) {
        return peopleRepository.save(people);
    }

    @Override
    public void updatePeople(People people) {
        peopleRepository.save(people);
    }

    @Override
    public void deletePeople(int peopleId) {
        peopleRepository.deleteById(peopleId);
    }
}

