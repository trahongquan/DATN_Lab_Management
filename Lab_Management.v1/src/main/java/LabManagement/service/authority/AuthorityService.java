package LabManagement.service.authority;

import LabManagement.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> getAllAuthorities();

    Authority createAuthority(Authority authority);

    Authority findAuthorityByUsername(String username);

    void deleteAuthority(String username);
}
