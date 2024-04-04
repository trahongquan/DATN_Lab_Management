package LabManagement.service.authority;

import LabManagement.entity.authority;

import java.util.List;

public interface AuthorityService {

    List<authority> getAllAuthorities();

    authority createAuthority(authority authority);

    void deleteAuthority(String username);
}
