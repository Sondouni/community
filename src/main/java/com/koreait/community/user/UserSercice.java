package com.koreait.community.user;

import com.koreait.community.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSercice {
    @Autowired
    UserMapper mapper;
    //회원조회
    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        return mapper.selUser(entity)==null?1:0;
    }
    //회원가입
    public int updUser(UserEntity entity){
        entity.setUpw(BCrypt.hashpw(entity.getUpw(),BCrypt.gensalt()));
        int result =  mapper.updUser(entity);

        return result;
    }

}
