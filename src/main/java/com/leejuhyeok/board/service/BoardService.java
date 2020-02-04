package com.leejuhyeok.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leejuhyeok.board.domain.Board;


public interface BoardService {
	void insertBoard(Board board);
	void updateBoard(Long boardId, Board newBoard);
	void deleteBoard(Long boardId);
	Page<Board> findBoardList(Pageable pageable);

}
