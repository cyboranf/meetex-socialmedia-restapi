package com.example.meetexApi.seed;

import com.example.meetexApi.model.Activities;
import com.example.meetexApi.service.ActivitiesService;
import com.example.meetexApi.service.UserService;

public class ActivitiesInitClass {
    private final ActivitiesService activitiesService;
    private final UserService userService;

    public ActivitiesInitClass(ActivitiesService activitiesService, UserService userService) {
        this.activitiesService = activitiesService;
        this.userService = userService;
    }

    public void initActivities(){
        Activities activitiesNo1=new Activities();
        Activities activitiesNo2=new Activities();
        Activities activitiesNo3=new Activities();
        Activities activitiesNo4=new Activities();
        Activities activitiesNo5=new Activities();
        Activities activitiesNo6=new Activities();

        activitiesNo1.setName("Hiking");
        activitiesNo2.setName("Travelling");
        activitiesNo3.setName("Cooking");
        activitiesNo4.setName("Rock and Roll");
        activitiesNo5.setName("Hockey");
        activitiesNo6.setName("Basket");

        activitiesService.save(activitiesNo1);
        activitiesService.save(activitiesNo2);
        activitiesService.save(activitiesNo3);
        activitiesService.save(activitiesNo4);
        activitiesService.save(activitiesNo5);
        activitiesService.save(activitiesNo6);
    }
}
