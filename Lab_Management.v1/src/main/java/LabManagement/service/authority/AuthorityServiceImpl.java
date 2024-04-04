package LabManagement.service.authority;

import LabManagement.dao.AuthorityRepository;
import LabManagement.entity.authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public authority createAuthority(authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void deleteAuthority(String username) {
        authorityRepository.deleteByUsername(username);
    }
}
