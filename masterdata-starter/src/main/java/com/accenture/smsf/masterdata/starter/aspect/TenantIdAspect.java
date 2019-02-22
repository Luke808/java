package com.accenture.smsf.masterdata.starter.aspect;

import com.accenture.smsf.framework.boot.stereotype.Component;
import com.accenture.smsf.framework.starter.web.principal.TenantHolder;
import com.accenture.smsf.model.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.persistence.Table;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Aspect
@Slf4j
@Component
public class TenantIdAspect {

    @Pointcut("execution(* com.accenture.smsf.masterdata.service.*Service.*(..))")
    public void pointcut(){};

    @Before("pointcut()")
    public void setTenantId(JoinPoint joinPoint) throws ApplicationException {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length ==0) {
            return;
        }
        if (args[0] != null && isEntity(args[0])) {
            setTenantid(args[0]);
        }
        if (args[0] != null && args[0] instanceof List) {
            List list = (List) args[0];
            if (!list.isEmpty() && isEntity(list.get(0))) {
                for (Object o : list) {
                    setTenantid(o);
                }
            }
        }
    }

    private boolean isEntity(Object obj) {
        return !Objects.isNull(obj.getClass().getAnnotation(Table.class));
    }

    private void setTenantid(Object obj) throws ApplicationException {
        try {
            Method setter = obj.getClass().getDeclaredMethod("setTenantid");
            setter.invoke(obj, TenantHolder.get());
        } catch (ReflectiveOperationException e) {
            log.error("ReflectiveOperationException");
            throw new ApplicationException();
        }
    }

}
