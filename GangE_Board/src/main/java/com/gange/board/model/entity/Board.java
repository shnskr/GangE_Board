package com.gange.board.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@SequenceGenerator(name = "boardSeq", sequenceName = "BOARD_SEQ", allocationSize = 1)
@Table(name = "BOARD")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardSeq")
	@Column(name = "BOARD_NO")
	private int boardNo;

//	@Size(min=1, message="제목을 입력해주세요")
	@Column(name = "TITLE")
	private String title;

//	@Size(min=1, message="내용을 입력해주세요")
	@Column(name = "CONTENTS")
	private String contents;

	@Column(name = "WRITER_NO")
	private int writerNo;

	@ManyToOne
	@JoinColumn(name = "WRITER_NO", insertable = false, updatable = false)
	private Member member;

	@CreationTimestamp // SYSDATE로 추가하기 위한 어노테이션
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") // 패턴에 맞는 문자열로 입력 받을시 Date 타입으로 자동 변환
	@Column(name = "REG_DATE")
	private Date regDate;

	@Column(name = "VIEW_CNT")
	private int viewCnt;

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getWriterNo() {
		return writerNo;
	}

	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", contents=" + contents + ", writerNo=" + writerNo
				+ ", regDate=" + regDate + ", viewCnt=" + viewCnt + "]";
	}

}
