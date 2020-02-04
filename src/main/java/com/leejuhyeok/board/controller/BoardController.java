package com.leejuhyeok.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leejuhyeok.board.domain.Board;
import com.leejuhyeok.board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String boardEdit() {
		return "board";
	}

	@PostMapping("/insert")
	public void boardInsert(Board board) {
		boardService.insertBoard(board);
	}

	@RequestMapping("/update")
	public void boardUpdate(@RequestBody Board board) {
	}

	@RequestMapping("/delete")
	public void boardDelete() {
	}
}
