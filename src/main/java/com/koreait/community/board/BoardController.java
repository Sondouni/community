package com.koreait.community.board;

import com.koreait.community.Const;
import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, BoardDTO dto, Model model){
        dto.setIcategory(icategory);
        model.addAttribute(Const.BOARD_LIST,service.selBoardList(dto));
        model.addAttribute(Const.I_CATEGORY,icategory);
        return "board/list";
    }

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity){

        int result = service.insBoard(entity);
        return "redirect:/board/list/1";
    }

}
