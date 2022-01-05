package com.koreait.community.user.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity {
    private String uid;
    private String upw;
    private String nm;
    private String profileimg;
    private String rdt;
    private String mdt;
    private int iuser;
    private int gender;

}
