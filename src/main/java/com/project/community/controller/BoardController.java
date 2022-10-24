package com.project.community.controller;

import com.project.community.domain.BoardDto;
import com.project.community.domain.PageHandler;
import com.project.community.domain.SearchCondition;
import com.project.community.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @PostMapping("/modify")
    public String modify(Integer page, Integer pageSize, BoardDto boardDto, HttpSession session, Model m,RedirectAttributes redirectAttributes) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.modify(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Modify failed");
            } else {
                redirectAttributes.addFlashAttribute("msg", "MOD_OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "MOD_ERR");
            m.addAttribute("mode", "new");
            return "board";
        }
        return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
    }
    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model m,RedirectAttributes redirectAttributes) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Write failed");
            } else {
                redirectAttributes.addFlashAttribute("msg", "WRT_OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "WRT_ERR");
            m.addAttribute("mode", "new");
            return "board";
        }
        return "redirect:/board/list";
    }
    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board";
    }

    @GetMapping("/read")
    public String read(Integer bno,Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }
    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes redirectAttributes) {
        String writer = (String) session.getAttribute("id");
        try {

            int rowCnt = boardService.remove(bno, writer);
            if (rowCnt != 1) {
                throw new Exception("board remove error");
            } else {
                redirectAttributes.addFlashAttribute("msg", "DEL_OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "DEL_ERR");
        }
        return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
    }
    @GetMapping("/list")
    public String list(SearchCondition sc, Model m , HttpServletRequest request) {
        if (!loginCheck(request)) {
            return "redirect:/login/login?toURL=" + request.getRequestURL();
        }
//        if (page == null) {
//            page=1;
//        }
//        if (pageSize == null) {
//            pageSize =10;
//        }
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            Map map = new HashMap();
            map.put("offset", (sc.getPage() - 1) * sc.getPageSize());
            map.put("pageSize", sc.getPageSize());

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", sc.getPage());
            m.addAttribute("pageSize", sc.getPageSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        // 세션에 아이디가 있는지 확인해서 있으면 true 반환
        return session.getAttribute("id")!=null;
    }
}
