package com.koreait.community.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SendData {
    @Autowired
    private static RestTemplate restTemplate;

    public static ResponseEntity<SubmitData> sendEngine(){
        int SubNum = 123;
        int Pnum = 1;
        Object Pcode = "코드";
        SubmitData requestDto = SubmitData.builder()
                .SubNum(SubNum)
                .Pnum(Pnum)
                .Pcode(Pcode)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<SubmitData> entity = new HttpEntity<>(requestDto, headers);

        String url = "http://localhost:8080/send";

        return restTemplate.exchange(url, HttpMethod.POST, entity, SubmitData.class);
    }
}
