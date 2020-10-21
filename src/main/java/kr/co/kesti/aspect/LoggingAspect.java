package kr.co.kesti.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class LoggingAspect {
    @Before("")
    public void doBefore() {
        /**
         * 로직 추가
         * */
    }

    @After("")
    public void doAfter() {
        /**
         * 로직 추가
         * */
    }

    @AfterReturning("")
    public void doAfterReturning() {
        /**
         * 로직 추가
         * */
    }

    @AfterThrowing("")
    public void doAfterThrowing() {
        /**
         * 로직 추가
         * */
    }

    @Around("")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            /**
             * Before 로직 추가
             * */

            obj = joinPoint.proceed();

            /**
             * After 로직 추가
             * */
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}