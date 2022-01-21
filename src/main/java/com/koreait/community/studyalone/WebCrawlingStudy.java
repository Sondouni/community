package com.koreait.community.studyalone;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCrawlingStudy {
    public static String getData(){
        final String Url = "https://place.map.kakao.com/1767432954";
        Connection conn = Jsoup.connect(Url);
        try {
            Document document = conn.get();
            Elements elements = document.select("div.details_placeinfo");
            System.out.println(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
