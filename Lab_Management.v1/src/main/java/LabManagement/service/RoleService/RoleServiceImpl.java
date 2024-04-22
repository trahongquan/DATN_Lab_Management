package LabManagement.service.RoleService;

import LabManagement.entity.Roles;
import LabManagement.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
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

