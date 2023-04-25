//package com.example.meetexApi.seed;
//
//import com.example.meetexApi.model.CompanyPage;
//import com.example.meetexApi.service.PagesService;
//import com.example.meetexApi.service.UserService;
//
//public class PagesInitClass {
//    private final PagesService pagesService;
//    private final UserService userService;
//
//    public PagesInitClass(PagesService pagesService, UserService userService) {
//        this.pagesService = pagesService;
//        this.userService = userService;
//    }
//
//    public void initPages() {
//        CompanyPage pageNo1 = new CompanyPage();
//        CompanyPage pageNo2 = new CompanyPage();
//        CompanyPage pageNo3 = new CompanyPage();
//
//        pageNo1.setName("Script House");
//        pageNo2.setName("Phoenix Team");
//        pageNo3.setName("Java Developers");
//
//        pagesService.save(pageNo1);
//        pagesService.save(pageNo2);
//        pagesService.save(pageNo3);
//    }
//}
