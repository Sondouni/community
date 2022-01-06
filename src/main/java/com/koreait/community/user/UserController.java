package com.koreait.community.user;

import com.koreait.community.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            reAttr.addFlashAttribute("errMsg","not possible to join");
            return "redirect:/user/join";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/idchk/{uid}")
    @ResponseBody
    public Map<String,Integer> idChk(@PathVariable String uid){
        System.out.println("uid : "+ uid);
        Map<String,Integer> res = new HashMap();
        res.put("result", sercice.idChk(uid));
        return res;
    }
}
