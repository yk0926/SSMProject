package yk.ssm.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import yk.ssm.domain.SysLog;
import yk.ssm.service.SysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;


    @Before("execution(* yk.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime=new Date();
        clazz=jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if(args==null||args.length==0){
            method=clazz.getMethod(methodName);
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }

        
    }

    @After("execution(* yk.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long executionTime = new Date().getTime()-visitTime.getTime();
        String url;
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            RequestMapping clazzAnnotation =(RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation!=null){
                String[] classValue = clazzAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                    String ip = request.getRemoteAddr();
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
