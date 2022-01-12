package com.koreait.community.board;

import com.koreait.community.Const;
import com.koreait.community.UserUtils;
import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardVO;
import com.koreait.community.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;
    @Autowired
    private UserUtils userUtils;
    //게시글 가져오기
    public List<BoardVO> selBoardList(BoardDTO dto){ return mapper.selBoardList(dto); }
    //게시글 디테일
    public BoardVO serBoardDetail(BoardDTO dto){
        BoardVO detail = mapper.selBoardDetail(dto);
        //조회수 작업
        if(dto.getLastip() != null && !Objects.equals(dto.getLastip(), detail.getLastip())){
            int resultHit = mapper.updHits(dto);
            if(resultHit==1){
                detail.setHits(detail.getHits()+1);
            }
        }
        return detail;
    }
    //글쓰기
    public int insBoard(BoardEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoard(entity);
    }
    //글삭제
    public int delBoard(BoardEntity entity){
        entity.setIsdel(1);
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.updBoard(entity);
    }
    //글수정
    public int chgBoard(BoardEntity dto){
        dto.setIuser(userUtils.getLoginUserPk());
        return mapper.updBoard(dto);
    }
}
