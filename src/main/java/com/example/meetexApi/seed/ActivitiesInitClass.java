//package com.example.meetexApi.seed;
//
//import com.example.meetexApi.model.Activity;
//import com.example.meetexApi.service.ActivitiesService;
//import com.example.meetexApi.service.UserService;
//
//public class ActivitiesInitClass {
//    private final ActivitiesService activitiesService;
//    private final UserService userService;
//
//    public ActivitiesInitClass(ActivitiesService activitiesService, UserService userService) {
//        this.activitiesService = activitiesService;
//        this.userService = userService;
//    }
//
//    public void initActivities(){
//        Activity activityNo1 =new Activity();
//        Activity activityNo2 =new Activity();
//        Activity activityNo3 =new Activity();
//        Activity activityNo4 =new Activity();
//        Activity activityNo5 =new Activity();
//        Activity activityNo6 =new Activity();
//
//        activityNo1.setName("Hiking");
//        activityNo2.setName("Travelling");
//        activityNo3.setName("Cooking");
//        activityNo4.setName("Rock and Roll");
//        activityNo5.setName("Hockey");
//        activityNo6.setName("Basket");
//
//        activitiesService.save(activityNo1);
//        activitiesService.save(activityNo2);
//        activitiesService.save(activityNo3);
//        activitiesService.save(activityNo4);
//        activitiesService.save(activityNo5);
//        activitiesService.save(activityNo6);
//    }
//}
