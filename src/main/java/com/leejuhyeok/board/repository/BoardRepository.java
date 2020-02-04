package com.leejuhyeok.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leejuhyeok.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	@Modifying
	@Query(value = "UPDATE board SET views = ")
	void viewUpate(@Param("idx") Long idx);
}
