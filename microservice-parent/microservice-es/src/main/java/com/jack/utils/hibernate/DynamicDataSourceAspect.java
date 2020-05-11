package com.jack.utils.hibernate;

import com.jack.utils.log.LogData;
import com.jack.utils.log.LogMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ：liyongjie
 * @ClassName ：DynamicDataSourceAspect
 * @date ： 2019-11-28 09:56
 * 通过注解的形式在service全部加载之前执行
 * @modified By：
 */
@Aspect
@Order(0)
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    public DynamicDataSourceAspect() {
    }

    @Pointcut("execution(* com.jack..service..*.*(..))")
    public void dynamicDs() {
    }

    @Before("dynamicDs()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = (DataSource)method.getAnnotation(DataSource.class);
                HandleDataSource.putDataSource(dataSource.readOnly());
                logger.debug((new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(), "DynamicDataSourceAspect doBefore,methodName=" + method.getName() + ",HandleDataSource readOnly=" + dataSource.readOnly())).toJson());
            } else {
                HandleDataSource.putDataSource(false);
                logger.debug((new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(), "DynamicDataSourceAspect doBefore,methodName=" + method.getName() + ",use the default DataSource(master)")).toJson());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    @After("dynamicDs()")
    public void doAfter() {
        try {
            HandleDataSource.remove();
            logger.debug((new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(), "DynamicDataSourceAspect doAfter,remove ThreadLocal DataSource")).toJson());
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
