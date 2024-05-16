package LabManagement.service.RoleService;

import LabManagement.ClassSuport.RoleSystem;
import LabManagement.entity.Roles;
import LabManagement.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Roles findByRoleId(int roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public List<Roles> getAllRolesNotRoleSystem() {
        List<Roles> roles = roleRepository.findAll();
        RoleSystem.ROLE.forEach(roleSystem -> roles.removeIf(role ->roleSystem.equals(role.getRole())));
        System.out.println(RoleSystem.ROLE);
        return roles;
    }

    @Override
    public List<Roles> getAllRoles(){
        List<Roles> roles = roleRepository.findAll();
        roles.removeIf(role -> "ROLE_TAECHERWAITCANCEL".equals(role.getRole()));
        return roles;
    }



    @Override
    public Roles createRole(Roles role) {
        return roleRepository.save(role);
    }

    @Override
    public void updateRole(Roles role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(int roleId) {
        roleRepository.deleteById(roleId);
    }
}

