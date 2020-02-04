package com.leejuhyeok.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leejuhyeok.board.domain.Board;
import com.leejuhyeok.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String boardEdit() {
		return "board";
	}
	
	@RequestMapping("/page/{idx}")
	public String getBoardPage(@PathVariable("idx") Long idx, Model model) {
		Board board = boardService.getOne(idx);
		board.setViews(board.getViews()+1L);
		model.addAttribute("board",board);
		return "board";
	}

	@PostMapping("/insert")
	public ResponseEntity<?> boardInsert(@RequestBody Board board) {
		boardService.insertBoard(board);
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}

	@PutMapping("/update/{idx}")
	public ResponseEntity<?> boardUpdate(@PathVariable("idx") Long idx ,@RequestBody Board board) {
		boardService.updateBoard(idx, board);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idx}")
	public ResponseEntity<?> boardDelete(@PathVariable("idx") Long idx) {
		boardService.deleteBoard(idx);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}
