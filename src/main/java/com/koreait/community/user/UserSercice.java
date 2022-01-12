package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.MyFileUtils;
import com.koreait.community.UserUtils;
import com.koreait.community.user.model.UserEntity;
import org.springframework.beans.BeanUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Service
public class UserSercice {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private MyFileUtils fileUtils;
    @Autowired
    private UserUtils userUtils;
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
            session.setAttribute(Const.LOGIN_USER,loginUser);
            return 1;
        }
        return 2;
    }

    public void logout(){
        session.invalidate();
    }

    //프로필 사진 저장 path리턴
   public String  uploadProfileImg(MultipartFile mf){
        if(mf == null){return null;}
        UserEntity loginUser = userUtils.getLoginUser();
        //프로필사진 저장경로
        final String PATH = Const.UPLOAD_IMG_PATH+"/user/"+loginUser.getIuser();
        //파일 사진 저장,이름바꾸기
        String filenm = fileUtils.saveFile(PATH,mf);
        //프로필변경
       if(filenm==null){
           return null;
       }

        //세션에 담긴 기존파일명 가져오기

        String oldFilePath = PATH + "/" + loginUser.getProfileimg();
        fileUtils.delFile(oldFilePath);

        //프로필 업데이트
        UserEntity entity = new UserEntity();
        entity.setIuser(loginUser.getIuser());
        entity.setProfileimg(filenm);
        mapper.updProfile(entity);
        //세션에 새로담기
        loginUser.setProfileimg(filenm);
        System.out.println("fileNm : "+filenm);
        return filenm;
    }

}
