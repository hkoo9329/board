package com.leejuhyeok.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leejuhyeok.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	@Transactional
	@Modifying
	@Query(value = "update board set views = views + 1 where idx = :idx", nativeQuery = true)
	void viewUpate(@Param("idx") Long idx);
}
