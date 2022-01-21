package com.koreait.community.studyalone;

import com.koreait.community.studyalone.model.BroadMatVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BraodService {
    @Autowired
    private BroadMapper mapper;

    //맛집리스트
    public List<BroadMatVO> selBraodMatList(){
        return mapper.selBraodMatList();
    }
    //크롤링
    public String webCrawling(){
        return WebCrawlingStudy.getData();
    }
}
