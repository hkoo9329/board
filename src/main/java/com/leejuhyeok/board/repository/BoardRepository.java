package com.leejuhyeok.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leejuhyeok.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

}
