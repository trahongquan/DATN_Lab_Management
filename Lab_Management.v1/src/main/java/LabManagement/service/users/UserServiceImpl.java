package LabManagement.service.users;


import LabManagement.dao.UserRepository;
import LabManagement.entity.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public users createUser(users user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(users user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
    @Override
    public void save(users user){
        userRepository.save(user);
    };
}
