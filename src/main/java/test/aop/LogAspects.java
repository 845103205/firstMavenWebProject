package test.aop;


import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class LogAspects {
    //方法执行前
    public void logStart(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"——start运行参数是——"+ Arrays.asList(joinPoint.getArgs()));
    }

    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"——end运行参数是——"+ Arrays.asList(joinPoint.getArgs()));
    }

    public void logReturn(Object result){
        System.out.println("——return运行参数是——"+ result);
    }

    public void logException(Exception exception){
        System.out.println("——exception运行参数是——"+exception);
    }
}
