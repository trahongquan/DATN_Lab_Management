package LabManagement.dao;

import LabManagement.entity.authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<authority, String> {
    public authority findByUsername(String user);
    public void deleteByUsername(String username);
}

