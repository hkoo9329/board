package com.leejuhyeok.board.controller;

import com.leejuhyeok.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leejuhyeok.board.domain.Comment;
import com.leejuhyeok.board.domain.User;
import com.leejuhyeok.board.repository.CommentRepository;
import com.leejuhyeok.board.repository.UserRepository;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/board/comment")
public class CommentController {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
	private HttpSession httpSession;

    @RequestMapping("/insert/{idx}")
    public ResponseEntity<?> commentInsert(@RequestBody Comment comment ,@PathVariable("idx") Long boardIdx) throws Exception {
    	User user = (User) httpSession.getAttribute("user");
    	log.info(user.getNickName());
    	commentRepository.save(Comment.builder()
                .comment(comment.getComment())
                .user(user)
                .date(LocalDateTime.now())
                .board(boardService.getOne(boardIdx))
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
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }


}
