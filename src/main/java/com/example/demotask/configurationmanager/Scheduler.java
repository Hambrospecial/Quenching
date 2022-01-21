//package com.example.demotask.configurationmanager;
//
//import com.example.demotask.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class Scheduler {
//    @Autowired
//    UserService userService;
//
//    @Scheduled(fixedRate = 60000L)
//    public void scheduleTaskWithFixedRate() {
//        userService.deactivatedUserScheduler();
//    }
//}
