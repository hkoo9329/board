package com.leejuhyeok.board.controller;

import com.leejuhyeok.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leejuhyeok.board.domain.Comment;
import com.leejuhyeok.board.repository.CommentRepository;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/board/comment")
public class CommentController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/insert/{idx}")
    public ResponseEntity<?> commentInsert(@RequestBody Comment comment,
                                           @PathVariable("idx") Long idx) throws Exception {
        commentRepository.save(Comment.builder()
                .comment(comment.getComment())
                .date(LocalDateTime.now())
                .board(boardService.getOne(idx))
                .build());
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @RequestMapping("/update")
    public ResponseEntity<?> commentUpdate(@RequestBody Comment comment) throws Exception {
        commentRepository.save(comment);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @RequestMapping("/delete/{idx}")
    public ResponseEntity<?> commentDelete(@PathVariable("idx") Long idx) throws Exception {
        commentRepository.deleteById(idx);
        ;
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }


}
