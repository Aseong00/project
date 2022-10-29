package com.project.community.controller;

import com.project.community.domain.CommentDto;
import com.project.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PatchMapping("/comments/{cno}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        commentDto.setCommenter(commenter);
        commentDto.setCno(cno);
        System.out.println("dto = " + commentDto);

        try {
            if(commentService.modify(commentDto)!=1)
                throw new Exception("Modify failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
        String commenter = "asdf";
        commentDto.setCommenter(commenter);
        commentDto.setBno(bno);
        System.out.println("commentDto = " + commentDto);
        try {
            if (commentService.write(commentDto) != 1) {
                throw new Exception("Write failed.");
            }
            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("WRE_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{cno}")
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
//        String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";

        try {
            int rowCnt = commentService.remove(cno, bno, commenter);

            if (rowCnt != 1) {
                throw new Exception("Delete Failed");
            }
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;
        try {
            list = commentService.getList(bno);
            System.out.println("list = " + list);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }
    }
}
