package com.gange.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gange.board.model.entity.Member;

public interface AccountRepository extends JpaRepository<Member, Integer>{
	int countById(String id);
	Member findById(String id);
}
