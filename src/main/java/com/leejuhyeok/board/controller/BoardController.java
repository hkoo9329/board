package com.leejuhyeok.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leejuhyeok.board.annotation.Socialuser;
import com.leejuhyeok.board.domain.Board;
import com.leejuhyeok.board.domain.SessionUser;
import com.leejuhyeok.board.domain.User;
import com.leejuhyeok.board.repository.CommentRepository;
import com.leejuhyeok.board.repository.UserRepository;
import com.leejuhyeok.board.service.BoardService;
import com.mysql.cj.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private HttpSession httpSession;

	

	@RequestMapping("")
	public String boardEdit(Model model) {
		User user = (User) httpSession.getAttribute("user");
		model.addAttribute("user", userRepository.getByEmail(user.getEmail()));
		return "board";
	}

	@RequestMapping("/page/{idx}")
	public String getBoardPage(HttpServletResponse response, HttpServletRequest request,
		@PathVariable("idx") Long idx,Model model) {
		Board board = boardService.getOne(idx);
		Cookie cookies[] = request.getCookies();
		Map<String, String> mapCookie = new HashMap();
		// ���옣�맂 荑좏궎 遺덈윭�삤湲�
		if (request.getCookies() != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie obj = cookies[i];
				mapCookie.put(obj.getName(), obj.getValue());
			}
		}
		// ���옣�맂 荑좏궎以묒뿉 read_count 留� 遺덈윭�삤湲�
		String cookie_read_count = (String) mapCookie.get("read_count");
		// ���옣�맆 �깉濡쒖슫 荑좏궎媛� �깮�꽦
		String new_cookie_read_count = "|" + idx;

		// ���옣�맂 荑좏궎�뿉 �깉濡쒖슫 荑좏궎媛믪씠 議댁옱�븯�뒗 吏� 寃��궗
		if (StringUtils.indexOfIgnoreCase(cookie_read_count, new_cookie_read_count) == -1) {
			// �뾾�쓣 寃쎌슦 荑좏궎 �깮�꽦
			Cookie cookie = new Cookie("read_count", cookie_read_count + new_cookie_read_count);

			response.addCookie(cookie);
			//議고쉶�닔 �뾽�뜲�씠�듃
			boardService.viewsUpdate(idx);
		}

		User user = (User) httpSession.getAttribute("user");
		model.addAttribute("user", userRepository.getByEmail(user.getEmail()));
		model.addAttribute("board", board);
		model.addAttribute("commentList", commentRepository.findByBoardIdx(board.getIdx()));
		return "board";
	}

	@PostMapping("/insert/{idx}")
	public ResponseEntity<?> boardInsert(@RequestBody Board board, @PathVariable("idx") Long userIdx) {
		
		boardService.insertBoard(Board.builder()
				.title(board.getTitle())
				.content(board.getContent())
				.user(userRepository.getOne(userIdx))
				.build());
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}

	@PutMapping("/update/{idx}")
	public ResponseEntity<?> boardUpdate(@PathVariable("idx") Long idx, @RequestBody Board board) {
		boardService.updateBoard(idx, board);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{idx}")
	public ResponseEntity<?> boardDelete(@PathVariable("idx") Long idx) {
		boardService.deleteBoard(idx);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

}
