package com.gange.board.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity	
@SequenceGenerator(
		name="memberSeq",
		sequenceName="MEMBER_SEQ",
		allocationSize=1)
@Table(name="MEMBER")
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="memberSeq")
	@Column(name="MEMBER_NO")
	private int memberNO;
	
//	@NotNull
	@Column(name="ID")
	private String id;
	
//	@NotNull
	@Column(name="PASSWORD")
	private String password;
	
	@CreationTimestamp // SYSDATE로 추가하기 위한 어노테이션
	@Column(name="REG_DATE")
	private Date regDate;

	public int getMemberNO() {
		return memberNO;
	}

	public void setMemberNO(int memberNO) {
		this.memberNO = memberNO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
