package com.koreait.community.board.fav;

import com.koreait.community.UserUtils;
import com.koreait.community.board.fav.model.BoardFavEntity;
import com.koreait.community.board.fav.model.BoardFavVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavService {
    @Autowired
    FavMapper mapper;
    @Autowired
    UserUtils userUtils;
    //좋아요 등록
    public int insBoardFav(BoardFavEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoardFav(entity);
    }
    //좋아요 가져오기
    public int selBoardFav(int iboard){
        BoardFavEntity result = mapper.selBoardFav(makeBoardFavEntity(iboard));
        if(result==null){
            return 0;
        }
        return 1;
    }
    //좋아요 삭제
    public int delBoardFav(int iboard){
        return mapper.delBoardFav(makeBoardFavEntity(iboard));
    }
    //좋아요 리스트 가져오기
    public List<BoardFavVO> selBoardFavList(int iboard){
        return mapper.selBoardFavList(makeBoardFavEntity(iboard));
    }

    //별점
    public int updBoardRate(BoardFavEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.updBoardRate(entity);
    }

    //BoardFavEntity 만들어주기
    private BoardFavEntity makeBoardFavEntity(int iboard){
        BoardFavEntity entity = new BoardFavEntity();
        entity.setIboard(iboard);
        entity.setIuser(userUtils.getLoginUserPk());
        return entity;
    }
}
