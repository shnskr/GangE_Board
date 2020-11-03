package com.gange.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gange.board.model.entity.Member;
import com.gange.board.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
//	@Autowired
//	private AccountRepository accountRepo;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/signup")
	public String signupGet(Model model) {
//		model.addAttribute("member", new Member());
		return "account/signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(Member member) {
		accountService.save(member);
		return "redirect:/";
	}
	
	@GetMapping("/idcheck")
	@ResponseBody
	public boolean idCheck(@RequestParam String id) throws Exception{
		return accountService.countById(id);
	}
}
