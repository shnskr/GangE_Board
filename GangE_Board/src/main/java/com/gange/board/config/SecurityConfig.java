package com.gange.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// 비밀번호를 암호화 할수 있도록 Bean 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// static 폴더의 하위 파일들은 인증을 무시 꺄륵꺄르륵
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/account/**").permitAll() // 해당 경로는 누구든 접근가능 꺄륵
			.anyRequest().authenticated() // 그 밖에 경로는 인증된 사용자만 접근 가능 깨륵개르륵
			.and()
		.formLogin()
			.loginPage("/") // 로그인 페이지 연결
			.loginProcessingUrl("/login") // login form의 action과 일치 시켜야한다
			.failureUrl("/?error=true") // 로그인 실패시 경호
			.defaultSuccessUrl("/") // 로그인 성공시 연결 페이지
			.usernameParameter("id") // 파라미터 이름 username을 id로 변경
			.passwordParameter("password")
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/");
//			.invalidateHttpSession(true) 로르아웃시 세션을 지우고 초기화한다 (기본값 true) 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
