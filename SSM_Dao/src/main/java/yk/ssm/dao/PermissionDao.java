package yk.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import yk.ssm.domain.Permission;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission where id in(select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;
    @Select("select * from permission")
    List<Permission> findAll() throws Exception;
    @Insert("insert into permission(id,permissionName,url) values(UUID(),#{permissionName},#{url})")
    void save(Permission permission);
}
