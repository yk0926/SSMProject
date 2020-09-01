package yk.ssm.service;

import yk.ssm.domain.Permission;
import yk.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll() throws  Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
