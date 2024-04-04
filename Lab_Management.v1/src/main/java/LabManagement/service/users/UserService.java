package LabManagement.service.users;

import LabManagement.entity.users;

import java.util.List;

public interface UserService {

    users findByUsername(String username);

    List<users> getAllUsers();

    users createUser(users user);

    void updateUser(users user);

    void deleteUser(String username);
    public void save(users user);
}
