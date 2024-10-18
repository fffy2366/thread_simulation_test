package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
public class SimulationController {

    @Autowired
    private Executor taskExecutor;

    @GetMapping("/simulate")
    public String simulateWork() throws InterruptedException {
        // taskExecutor.execute(() -> {
        //     try {
        //         Thread.sleep(10000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // });
        // 模拟同步耗时操作
        Thread.sleep(20000);
        return "任务已提交";
    }
}
