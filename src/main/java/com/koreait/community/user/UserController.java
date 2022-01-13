package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.UserUtils;
import com.koreait.community.user.model.UserDTO;
import com.koreait.community.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserSercice sercice;

    //회원가입
    @GetMapping("/join")
    public void join(){}

    @PostMapping("/join")
    public String join(UserEntity entity, RedirectAttributes reAttr){
        int result = sercice.updUser(entity);
        if(!(result==1)){
            reAttr.addFlashAttribute(Const.MSG,Const.ERR_4);
            return "redirect:/user/join";
        }
        //회원가입 후 로그인
        sercice.selLogin(entity);
        return "redirect:/board/list/1";
    }

    @GetMapping("/login")
    public void login(){}

    @PostMapping("/login")
    public String loginProc(UserEntity entity,RedirectAttributes reAttr){
        //reAttr.addFlashAttribute는 세션에 담아서 옮겨주고 리퀘스트에 담은후 사라짐
        int result = sercice.selLogin(entity);
        System.out.println(result);
        if(result!=1){
            reAttr.addFlashAttribute(Const.TRY_LOGIN,entity);
            switch (result){
                case 0:
                    reAttr.addFlashAttribute(Const.MSG,Const.ERR_2);
                    break;
                case 2:
                    reAttr.addFlashAttribute(Const.MSG,Const.ERR_3);
                    break;
                case 3:
                    reAttr.addFlashAttribute(Const.MSG,Const.ERR_1);
                    break;
            }
            return "redirect:/user/login";

        }
        return "redirect:/board/list/1";
    }

    @GetMapping("/logout")
    public String logout(){
        sercice.logout();
        return "redirect:/user/login";
    }

    @GetMapping("/idchk/{uid}")
    @ResponseBody
    public Map<String,Integer> idChk(@PathVariable String uid){
        System.out.println("check uid : "+ uid);
        Map<String,Integer> res = new HashMap();
        res.put("result", sercice.idChk(uid));
        return res;
    }
    //마이페이지
    //프로필
    @GetMapping("/mypage/profile")
    public void mypageProfile(){

    }
    @ResponseBody
    @PostMapping("/mypage/profile")
    public Map<String,String> mypageProfileProc(MultipartFile profileimg){
        String json = sercice.uploadProfileImg(profileimg);
        Map<String,String> result = new HashMap();
        System.out.println("json : "+json);
        result.put("result",json);
        return result;
    }
    //비밀번호
    @GetMapping("/mypage/modpassword")
    public void modPassword(){

    }
    @ResponseBody
    @PostMapping("/mypage/modpassword")
    public int modPassword(@RequestBody UserDTO dto){
        int result = sercice.selChkUpw(dto);
        System.out.println("비번 result = "+result);
        return result;
    }
}
