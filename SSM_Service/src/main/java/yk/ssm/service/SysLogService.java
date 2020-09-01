package yk.ssm.service;

import org.springframework.web.servlet.ModelAndView;
import yk.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {
    public void save(SysLog sysLog)throws Exception;

    List<SysLog> findAll() throws Exception;
}
