package com.koreait.community.board;

import com.koreait.community.CommonMapper;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private CommonMapper mapper;
    @Autowired
    private BoardService service;

    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, BoardDTO dto, Model model){
        dto.setIcategory(icategory);
        model.addAttribute("listTitle",mapper.selMenuCategoryList().get(icategory-1));
        model.addAttribute(Const.BOARD_LIST,service.selBoardList(dto));
        model.addAttribute(Const.I_CATEGORY,icategory);
        return "board/list";
    }

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity){
        int result = service.insBoard(entity);
        return "redirect:/board/list/"+entity.getIcategory();
    }
    //글 디테일
    @GetMapping("/detail")
    public void detail(BoardDTO dto, Model model, HttpServletRequest req){
        String lastIp = req.getHeader("X-FORWARDED-FOR");
        if(lastIp==null){
            lastIp= req.getRemoteAddr();
        }
        System.out.println("lastIp : " + lastIp);
        dto.setLastip(lastIp);
        BoardVO vo = service.serBoardDetail(dto);
        model.addAttribute(Const.BOARD_DETAIL,vo);
    }

    //글 수정
    @GetMapping("/change")
    public void change(BoardDTO dto,Model model){
        BoardVO vo = service.serBoardDetail(dto);
        model.addAttribute(Const.BOARD_DETAIL,vo);
    }
    @PostMapping("/change")
    public String changeProc(BoardDTO dto){
        int result = service.chgBoard(dto);
        return "redirect:/board/detail?iboard="+dto.getIboard();
    }
    //글 삭제
    @GetMapping("/delete")
    public String delete(BoardEntity entity){
        int result = service.delBoard(entity);
        return "redirect:/board/list/"+entity.getIcategory();
    }

}
