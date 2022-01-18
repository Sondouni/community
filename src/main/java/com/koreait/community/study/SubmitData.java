package com.koreait.community.study;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class SubmitData {
    private int SubNum;
    private int Pnum;
    private Object Pcode;
}
