package com.koreait.community.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO extends UserEntity{
    private String newUpw;
}
