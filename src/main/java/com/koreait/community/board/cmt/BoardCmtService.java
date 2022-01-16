package com.koreait.community.board.cmt;

import com.koreait.community.board.cmt.model.BoardCmtEntity;
import com.koreait.community.board.cmt.model.BoardCmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {
    @Autowired
    private BoardCmtMapper mapper;
    //댓글쓰기
    public int insCmt(BoardCmtEntity entity){
        return mapper.insCmt(entity);
    }
    //댓글 가져오기
    public List<BoardCmtVO> selCmtList(BoardCmtEntity entity){
        return mapper.selCmtList(entity);
    }
}
