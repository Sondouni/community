package com.koreait.community.board.cmt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtVO extends BoardCmtEntity{
    private String nm;
    private String profileimg;
}
