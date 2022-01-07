package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.user.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

@Service
public class UserSercice {
    @Autowired
    UserMapper mapper;
    @Autowired
    HttpSession session;
    //회원조회
    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        return mapper.selUser(entity)==null?1:0;
    }
    //회원가입
    public int updUser(UserEntity entity){
        UserEntity copyEntity = new UserEntity();
        BeanUtils.copyProperties(entity,copyEntity);

        System.out.println(" 가입시도 id : "+entity.getUid());
        System.out.println(" 가입시도 pw : "+entity.getUpw());
        copyEntity.setUpw(BCrypt.hashpw(entity.getUpw(),BCrypt.gensalt()));
        int result =  mapper.updUser(copyEntity);

        return result;
    }
    //로그인
    public int selLogin(UserEntity entity){
        UserEntity loginUser = null;

        try {
            loginUser = mapper.selUser(entity);

        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
        if(loginUser==null){
            return 0;
        }else if (loginUser.getUpw().length()<15){
            loginUser.setUpw(BCrypt.hashpw(loginUser.getUpw(),BCrypt.gensalt()));
        }
        if(loginUser!=null&&BCrypt.checkpw(entity.getUpw(),loginUser.getUpw())){
            loginUser.setUpw(null);
            loginUser.setRdt(null);
            session.setAttribute(Const.LOGIN_USER,loginUser);
            return 1;
        }
        return 2;
    }

}
