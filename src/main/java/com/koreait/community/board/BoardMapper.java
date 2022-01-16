package com.koreait.community.board;

import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardPrevNextVo;
import com.koreait.community.board.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //게시글 가져오기
    List<BoardVO> selBoardList(BoardDTO dto);
    //게시글 디테일
    BoardVO selBoardDetail(BoardDTO dto);
    //게시글 이전 다음글
    BoardPrevNextVo selPrevNext(BoardVO vo);
    //글쓰기
    int insBoard(BoardEntity entity);
    //조회수
    int updHits(BoardDTO dto);
    //수정
    int updBoard(BoardEntity dto);
    //삭제
    int delBoard(BoardDTO dto);

}
