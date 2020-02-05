package com.leejuhyeok.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leejuhyeok.board.domain.Board;
import com.leejuhyeok.board.repository.CommentRepository;
import com.leejuhyeok.board.service.BoardService;
import com.mysql.cj.util.StringUtils;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentRepository commentRepository;

	@RequestMapping("")
	public String boardEdit() {
		return "board";
	}
	
	@RequestMapping("/page/{idx}")
	public String getBoardPage(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("idx") Long idx, Model model) {
		Board board = boardService.getOne(idx);
		Cookie cookies[] = request.getCookies();
		Map<String,String> mapCookie = new HashMap();
		// ����� ��Ű �ҷ�����
		if(request.getCookies() != null) {
			for(int i=0; i< cookies.length; i++) {
				Cookie obj = cookies[i];
				mapCookie.put(obj.getName(), obj.getValue());
			}
		}
		// ����� ��Ű�߿� read_count �� �ҷ�����
		String cookie_read_count = (String) mapCookie.get("read_count");
		// ����� ���ο� ��Ű�� ����
		String new_cookie_read_count= "|" +idx;
		
		//����� ��Ű�� ���ο� ��Ű���� �����ϴ� �� �˻�
		if (StringUtils.indexOfIgnoreCase(cookie_read_count, new_cookie_read_count) == -1) {
			// ���� ��� ��Ű ����
			Cookie cookie = new Cookie("read_count", cookie_read_count + new_cookie_read_count);
			
			response.addCookie(cookie);
			//��ȸ�� ������Ʈ
			boardService.viewsUpdate(idx);
		}
		model.addAttribute("commentList",commentRepository.findAll());
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
