package LabManagement.service.RoleService;

import LabManagement.entity.Role;

import java.util.List;

public interface RoleService {

    Role findByRoleId(int roleId);

    List<Role> getAllRoles();

    Role createRole(Role role);

    void updateRole(Role role);

    void deleteRole(int roleId);
}
