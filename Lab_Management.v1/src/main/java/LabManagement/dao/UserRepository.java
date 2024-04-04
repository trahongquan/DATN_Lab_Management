package LabManagement.dao;

import LabManagement.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<users, Long> {

    users findByUsername(String username);
    public void deleteByUsername(String username);
}

