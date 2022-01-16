package com.koreait.community.board.cmt;

import com.google.gson.Gson;
import com.koreait.community.board.cmt.model.BoardCmtEntity;
import com.koreait.community.board.cmt.model.BoardCmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board/cmt")
public class BoardCmtController {
    @Autowired
    private BoardCmtService service;

    //댓글 가져오기
    @GetMapping("/list")
    public Map<String, List<BoardCmtVO>> selCmtList(@RequestParam("iboard") int iboard){
        Map<String, List<BoardCmtVO>> result= new HashMap<>();
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        Gson gson = new Gson();
        String json = gson.toJson(service.selCmtList(entity));
        result.put("result",service.selCmtList(entity));
        return result;
    }




    //댓글쓰기
    @PostMapping("/ins")
    public int insertCmt(@RequestBody BoardCmtEntity entity){
        return service.insCmt(entity);
    }
}
