package com.koreait.community.board.cmt;

import com.koreait.community.board.cmt.model.BoardCmtEntity;
import com.koreait.community.board.cmt.model.BoardCmtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    //댓글 쓰기
    int insCmt(BoardCmtEntity entity);
    //댓글 리스트
    List<BoardCmtVO> selCmtList(BoardCmtEntity entity);
    //댓글 삭제
    int delCmt(BoardCmtEntity entity);
    //댓글 수정
    int updCmt(BoardCmtEntity entity);
}
