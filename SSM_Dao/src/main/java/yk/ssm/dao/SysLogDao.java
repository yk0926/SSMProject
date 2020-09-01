package yk.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.servlet.ModelAndView;
import yk.ssm.domain.SysLog;

import java.util.List;

public interface SysLogDao {
    @Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method) " +
            "values(UUID(),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;
    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
