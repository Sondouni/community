package com.koreait.community.board.fav;

import com.koreait.community.board.fav.model.BoardFavEntity;
import com.koreait.community.board.fav.model.BoardFavVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavMapper {
    //좋아요 등록
    int insBoardFav(BoardFavEntity entity);
    //좋아요 확인
    BoardFavEntity selBoardFav(BoardFavEntity entity);
    //좋아요 취소
    int delBoardFav(BoardFavEntity entity);
    //좋아요 리스트
    List<BoardFavVO> selBoardFavList(BoardFavEntity entity);
    //별점
    int updBoardRate(BoardFavEntity entity);
}
