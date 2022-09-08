package demo.JPAApp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@RequiredArgsConstructor
public class StatsService {
    private Map<JoinPoint, Long> timeMap = new HashMap<JoinPoint, Long>();

    public void addExecutionTime(long time, JoinPoint joinPoint) {
        timeMap.put(joinPoint, time);
    }
}
