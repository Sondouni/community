package com.koreait.community.board;

import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //게시글 가져오기
    List<BoardVO> selBoardList(BoardDTO dto);
    //글쓰기
    int insBoard(BoardEntity entity);

}
