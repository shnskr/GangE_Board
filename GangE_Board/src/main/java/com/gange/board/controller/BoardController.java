package com.gange.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gange.board.model.dto.SecurityMember;
import com.gange.board.model.entity.Board;
import com.gange.board.repository.BoardRepository;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardRepository boardRepo;

	@GetMapping("/list")
	public String list(Model model,
			@PageableDefault(size = 10, sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Board> boards = boardRepo.findAll(pageable);

		// 현재 페이지
		int thisPage = boards.getNumber() + 1;
		// 시작 페이지
		int startPage = Math.max(1, (thisPage % 5 == 0) ? (thisPage - 4) : (thisPage / 5) * 5 + 1);
		// 끝 페이지
		int endPage = Math.max(1, Math.min(boards.getTotalPages(), startPage + 4));

		model.addAttribute("boards", boards);
		model.addAttribute("thisPage", thisPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "board/list";
	}

	@GetMapping("/write")
	public String writeGet(Model model) {
		return "board/write";
	}

	@PostMapping("/write")
	public String writePost(Board board) {
		SecurityMember member = (SecurityMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		board.setWriterNo(member.getMemberNo());
		System.out.println(board);
		boardRepo.save(board);

		return "redirect:/board/list";
	}
	
	@GetMapping("/{boardId}")
	public String detail(Model model, @PathVariable int boardId) {
		model.addAttribute("board", boardRepo.getOne(boardId));
		return "board/detail";
	}
	
	@GetMapping("/update/{boardId}")
	public String updateGet(Model model, @PathVariable int boardId) {
		SecurityMember member = (SecurityMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Board board = boardRepo.getOne(boardId);
		
		if (member.getMemberNo() == board.getWriterNo()) {
			model.addAttribute("board", board);
			return "board/update";
		} else {
			return "redirect:/board/list";
		}
	}
	
	@PostMapping("/update")
	public String updatePost(Board board) {
		System.out.println(board);
		boardRepo.save(board);
		return "redirect:/board/list";
	}
}
