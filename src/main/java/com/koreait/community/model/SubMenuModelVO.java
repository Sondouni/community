package com.koreait.community.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //맴버 받는 생성자
@NoArgsConstructor //기본생성자
public class SubMenuModelVO {
    private String href;
    private String nm;
}
