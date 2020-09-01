package yk.ssm.dao;

import org.apache.ibatis.annotations.*;
import yk.ssm.domain.Permission;
import yk.ssm.domain.Role;

import java.util.List;

public interface RoleDao {
    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "roleName" ,column = "roleName"),
            @Result(property = "roleDesc" ,column = "roleDesc"),
            @Result(property = "permissions" ,column = "id",javaType = java.util.List.class,many = @Many(select = "yk.ssm.dao.PermissionDao.findPermissionByRoleId")),
    }
    )
    public List<Role> findRoleByUserId(String userId) throws Exception;
    @Select("select * from role")
    List<Role> findAll() throws Exception;
    @Insert("insert into role(id,roleName,roleDesc) values(UUID(),#{roleName},#{roleDesc})")
    void save(Role role) throws  Exception;
    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId) throws Exception;
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId")  String permissionId);
}
