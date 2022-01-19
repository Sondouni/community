package com.koreait.community.board.fav.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFavEntity {
    private int iboard;
    private int iuser;
    private int irate;
    private String rdt;
}
