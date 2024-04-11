package LabManagement.service.authority;

import LabManagement.dao.AuthorityRepository;
import LabManagement.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void deleteAuthority(String username) {
        authorityRepository.deleteByUsername(username);
    }
}
