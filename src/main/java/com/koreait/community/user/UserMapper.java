package com.koreait.community.user;

import com.koreait.community.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //조회
    UserEntity selUser(UserEntity entity);
    //회원가입
    int updUser(UserEntity entity);

}
