package com.jun.cloud.common.common.annotation;

import com.jun.sail.utils.json.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AnnotationAspect {

    //拦截添加@SystemServiceLog的
    @Pointcut("@annotation(com.jun.cloud.common.common.annotation.SystemServiceLog)")
    public  void serviceAspect() {
    }

    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        //获得request和session
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列
        // 化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JsonUtil.toJson(joinPoint.getArgs()[i]) + ";";
            }
        }
        Logger log = getLog(joinPoint);
        try {
            /*==========数据库日志=========*/
            log.info("异常处理");
            log.info(e.getMessage());
            log.info((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.info(params);
            log.info(ip);
            //保存数据库
            //logService.add(log);
            System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {
            //记录本地异常日志
            log.error("==异常通知异常==");
            log.error("异常信息:{}", ex.getMessage());
        }
        /*==========记录本地异常日志==========*/
        log.error("异常方法:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() 
        + joinPoint.getSignature().getName(), e.getMessage(), params);

    }

    protected Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();
        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }
        return LoggerFactory.getLogger(getClass());
    }
}
