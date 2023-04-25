//package com.example.meetexApi.seed;
//
//import com.example.meetexApi.model.Community;
//
//import com.example.meetexApi.service.CommunityService;
//
//public class CommunityInitClass {
//    private final CommunityService communityService;
//
//    public CommunityInitClass(CommunityService communityService) {
//        this.communityService = communityService;
//    }
//
//    public void initPages() {
//        Community comNo1 = new Community();
//        Community comNo2 = new Community();
//        Community comNo3 = new Community();
//
//        comNo1.setName("Script House");
//        comNo2.setName("Phoenix Team");
//        comNo3.setName("Java Developers");
//
//        communityService.save(comNo1);
//        communityService.save(comNo2);
//        communityService.save(comNo3);
//    }
//
//}
