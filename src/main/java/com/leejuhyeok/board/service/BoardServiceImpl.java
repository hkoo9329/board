package com.leejuhyeok.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.leejuhyeok.board.domain.Board;
import com.leejuhyeok.board.domain.Comment;
import com.leejuhyeok.board.repository.BoardRepository;
import com.leejuhyeok.board.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void insertBoard(Board board) {
		// TODO Auto-generated method stub
		board.setCreateDateTime();
		board.setViews(0L);
		boardRepository.save(board);
		
	}

	@Override
	public void updateBoard(Long boardId, Board newBoard) {
		// TODO Auto-generated method stub
		Board oldBoard = boardRepository.getOne(boardId);
		boardRepository.save(oldBoard.update(newBoard));
		
	}

	@Override
	@Transactional
	public void deleteBoard(Long boardId) {
		// TODO Auto-generated method stub
		commentRepository.deleteByBoardIdx(boardId);
		boardRepository.deleteById(boardId);
	}

	@Override
	public Page<Board> findBoardList(Pageable pageable) {
		// TODO Auto-generated method stub
		if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                    pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "idx"));
        }
		return boardRepository.findAll(pageable);
	}

	@Override
	public Board getOne(Long boardId) {
		// TODO Auto-generated method stub
		return boardRepository.getOne(boardId);
	}

	@Override
	public void viewsUpdate(Long boardId) {
		// TODO Auto-generated method stub
		boardRepository.viewUpate(boardId);
		
	}
	
	

}
