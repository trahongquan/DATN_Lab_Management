package LabManagement.service.RoleService;

import LabManagement.entity.Roles;

import java.util.List;

public interface RoleService {

    Roles findByRoleId(int roleId);

    List<Roles> getAllRoles();

    Roles createRole(Roles role);

    void updateRole(Roles role);

    void deleteRole(int roleId);
}
