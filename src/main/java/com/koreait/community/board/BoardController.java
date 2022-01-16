package com.koreait.community.board;

import com.koreait.community.CommonMapper;
import com.koreait.community.Const;
import com.koreait.community.board.model.BoardDTO;
import com.koreait.community.board.model.BoardEntity;
import com.koreait.community.board.model.BoardPrevNextVo;
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
        BoardPrevNextVo pnVo = service.selPrevNext(vo);
        model.addAttribute(Const.BOARD_DETAIL,vo);
        model.addAttribute(Const.PREV_NEXT,pnVo);
        System.out.println(pnVo);
    }

    //글 수정
    @GetMapping("/change")
    public void change(BoardDTO dto,Model model){
        BoardVO vo = service.serBoardDetail(dto);
        model.addAttribute(Const.BOARD_DETAIL,vo);
    }
    @PostMapping("/change")
    public String changeProc(BoardEntity entity){
        int result = service.chgBoard(entity);
        return "redirect:/board/detail?iboard="+entity.getIboard();
    }
    //글 삭제
    @GetMapping("/delete")
    public String delete(BoardEntity entity){
        int result = service.delBoard(entity);
        return "redirect:/board/list/"+entity.getIcategory();
    }
    //글 수정,삭제
    @GetMapping("/mod")
    public void mod(BoardDTO dto,Model model){
        String title = "Write";
        if(dto.getIboard()!=0){
            BoardVO vo = service.serBoardDetail(dto);
            model.addAttribute(Const.BOARD_DETAIL,vo);
            title = "Change";
        }
        model.addAttribute("title",title);
    }
    @PostMapping("/mod")
    public String modProc(BoardEntity entity){
        System.out.println("mod포스트"+entity.getIboard());
        if(entity.getIboard()==0){
            int result = service.insBoard(entity);
            return "redirect:/board/list/"+entity.getIcategory();
        }
        int result = service.chgBoard(entity);
        return "redirect:/board/detail?iboard="+entity.getIboard();
    }
}
