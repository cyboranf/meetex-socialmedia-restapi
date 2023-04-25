package com.example.meetexApi.seed;

import com.example.meetexApi.model.Pages;
import com.example.meetexApi.service.PagesService;
import com.example.meetexApi.service.UserService;

public class PagesInitClass {
    private final PagesService pagesService;
    private final UserService userService;

    public PagesInitClass(PagesService pagesService, UserService userService) {
        this.pagesService = pagesService;
        this.userService = userService;
    }

    public void initPages() {
        Pages pageNo1 = new Pages();
        Pages pageNo2 = new Pages();
        Pages pageNo3 = new Pages();

        pageNo1.setName("Script House");
        pageNo2.setName("Phoenix Team");
        pageNo3.setName("Java Developers");

        pagesService.save(pageNo1);
        pagesService.save(pageNo2);
        pagesService.save(pageNo3);
    }
}
