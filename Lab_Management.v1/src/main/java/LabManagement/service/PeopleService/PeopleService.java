package LabManagement.service.PeopleService;

import LabManagement.entity.People;

import java.util.List;

public interface PeopleService {

    People findByPeopleId(int peopleId);

    List<People> getAllPeople();

    People createPeople(People people);

    void updatePeople(People people);

    void deletePeople(int peopleId);
}

