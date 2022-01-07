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

@Service
public class BoardService {
    @Autowired
    private BoardMapper mapper;
    @Autowired
    private UserUtils userUtils;
    //게시글 가져오기
    public List<BoardVO> selBoardList(BoardDTO dto){
        return mapper.selBoardList(dto);
    }
    //글쓰기
    public int insBoard(BoardEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoard(entity);
    }
}
