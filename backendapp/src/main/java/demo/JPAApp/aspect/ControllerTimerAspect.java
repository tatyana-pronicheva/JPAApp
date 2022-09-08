package demo.JPAApp.aspect;

import demo.JPAApp.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ControllerTimerAspect {
    private final StatsService statsService;
     Long startTime;

    @Pointcut("@annotation(ApplyTimer)")
    private void controllerMethod() {}

    @Before("controllerMethod()")
        public void timerBefore(JoinPoint joinPoint) {
            startTime = System.currentTimeMillis();
        }

    @After("controllerMethod()")
    public void timerAfter(JoinPoint joinPoint) {
        Long finishTime = System.currentTimeMillis();
        statsService.addExecutionTime(finishTime - startTime, joinPoint);
    }

}
