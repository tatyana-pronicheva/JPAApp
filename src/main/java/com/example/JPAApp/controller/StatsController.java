package com.example.JPAApp.controller;

import com.example.JPAApp.entity.Product;
import com.example.JPAApp.service.StatsService;
import com.example.JPAApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/executionTime")
    public Map<JoinPoint, Long> getExecutionTime(){
        return statsService.getTimeMap();
    }

}
