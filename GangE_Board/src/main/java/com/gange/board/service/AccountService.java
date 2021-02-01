package com.gange.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gange.board.model.dto.SecurityMember;
import com.gange.board.model.entity.Member;
import com.gange.board.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean countById(String id) throws Exception{
		return (accountRepo.countById(id) > 0) ? true : false;
	}
	
	public void save(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		accountRepo.save(member);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = accountRepo.findById(username);
		
		SecurityMember securityMember = new SecurityMember();
		
		if (member != null) {
			securityMember.setMemberNo(member.getMemberNo());
			securityMember.setUsername(member.getId());
			securityMember.setPassword(member.getPassword());
			securityMember.setRegDate(member.getRegDate());
		}
		
		return securityMember;
	}

}
