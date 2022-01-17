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
    @GetMapping("/{iboard}")
    public List<BoardCmtVO> selCmtList(@PathVariable int iboard){
        BoardCmtEntity entity = new BoardCmtEntity();
        System.out.println("iboard : "+iboard);
        entity.setIboard(iboard);

        return service.selCmtList(entity);
    }
    //댓글쓰기
    @PostMapping("/ins")
    public int insertCmt(@RequestBody BoardCmtEntity entity){
        return service.insCmt(entity);
    }

    //댓글 삭제
    @DeleteMapping("/{icmt}")
    public int deleteCmt(@PathVariable("icmt") int icmt){
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIcmt(icmt);
        return service.delCmt(entity);
    }

    //댓글 수정
    @PutMapping
    public int updCmt(@RequestBody  BoardCmtEntity entity){
        return service.updCmt(entity);
    }
}
