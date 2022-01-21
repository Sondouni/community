package com.koreait.community.studyalone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/broad")
public class BroadController {
    @Autowired
    private BraodService service;

    //리스트 가져오기
    @GetMapping("/list")
    public void selBraodMatList(Model model){
        model.addAttribute("list",service.selBraodMatList());
    }

    //크롤링
    @GetMapping("/crawling")
    public void getthatShit(Model model){
        String image = service.webCrawling();
        model.addAttribute("images", image.toString());
    }
}
