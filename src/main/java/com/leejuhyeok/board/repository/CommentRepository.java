package com.leejuhyeok.board.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leejuhyeok.board.domain.Board;
import com.leejuhyeok.board.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByBoardIdx(Long boardIdx);
	Optional<Comment> findByIdxAndBoardIdx(Long id, Long boardIdx);
}
