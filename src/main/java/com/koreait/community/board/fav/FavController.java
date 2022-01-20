package com.koreait.community.board.fav;

import com.koreait.community.Const;
import com.koreait.community.board.fav.model.BoardFavEntity;
import com.koreait.community.board.fav.model.BoardFavVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board/fav")
public class FavController {
    @Autowired
    FavService service;

    //좋아요 등록
    @PostMapping()
    public Map<String, Integer> insBoardFav(@RequestBody BoardFavEntity entity){
        int result = service.insBoardFav(entity);
        Map<String,Integer> map = new HashMap<>();
        map.put(Const.RESULT,result);
        return map;
    }
    //좋아요 가져오기
    @GetMapping("/{iboard}")
    public Map<String, Integer> selBoardFav(@PathVariable int iboard){
        int result = service.selBoardFav(iboard);
        Map<String,Integer> map = new HashMap<>();
        map.put(Const.RESULT,result);
        return map;
    }
    //좋아요 리스트 가져오기
    @GetMapping
    public Map<String, List<BoardFavVO>> selBoardFavList(@RequestParam int iboard){
        List<BoardFavVO> list = service.selBoardFavList(iboard);
        Map<String,List<BoardFavVO>> map = new HashMap<>();
        map.put(Const.RESULT,list);
        return map;
    }

    //좋아요 취소
    @DeleteMapping("/{iboard}")
    public Map<String, Integer> delBoardFav(@PathVariable int iboard){
        int result = service.delBoardFav(iboard);
        Map<String,Integer> map = new HashMap<>();
        map.put(Const.RESULT,result);
        return map;
    }

    //별점 등록/수정
    @PostMapping("/rate")
    public Map<String,Integer> updBoardRate(@RequestBody BoardFavEntity entity){
        System.out.println(entity.getIrate());
        int result = service.updBoardRate(entity);
        Map<String,Integer> map = new HashMap<>();
        map.put(Const.RESULT,result);
        return map;
    }
}
