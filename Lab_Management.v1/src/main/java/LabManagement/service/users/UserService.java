package LabManagement.service.users;

import LabManagement.entity.Users;

import java.util.List;

public interface UserService {

    Users findByUsername(String username);

    Users findByPeopleId(int id);

    List<Users> getAllUsers();

    Users createUser(Users user);

    void updateUser(Users user);

    void deleteUser(String username);

    public void save(Users user);
}
