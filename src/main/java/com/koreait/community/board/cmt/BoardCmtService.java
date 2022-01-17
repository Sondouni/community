package com.koreait.community.board.cmt;

import com.koreait.community.UserUtils;
import com.koreait.community.board.cmt.model.BoardCmtEntity;
import com.koreait.community.board.cmt.model.BoardCmtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {
    @Autowired
    private BoardCmtMapper mapper;
    @Autowired
    private UserUtils userUtils;

    //댓글쓰기
    public int insCmt(BoardCmtEntity entity){
        return mapper.insCmt(entity);
    }
    //댓글 가져오기
    public List<BoardCmtVO> selCmtList(BoardCmtEntity entity){
        return mapper.selCmtList(entity);
    }
    //댓글 지우기
    public int delCmt(BoardCmtEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.delCmt(entity);
    }
    //댓글 수정하기
    public int updCmt(BoardCmtEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.updCmt(entity);
    }
}
