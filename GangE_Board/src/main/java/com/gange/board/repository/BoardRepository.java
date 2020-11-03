package com.gange.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gange.board.model.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
