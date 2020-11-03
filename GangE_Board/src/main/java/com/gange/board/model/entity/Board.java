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
		name="boardSeq",
		sequenceName="BOARD_SEQ",
		allocationSize=1)
@Table(name="BOARD")
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="boardSeq")
	@Column(name="BOARD_NO")
	private int boardNo;
	
//	@Size(min=1, message="제목을 입력해주세요")
	@Column(name="TITLE")
	private String title;
	
//	@Size(min=1, message="내용을 입력해주세요")
	@Column(name="CONTENTS")
	private String contents;
	
	@Column(name="WRITER_NO")
	private int writerNo;
	
	@CreationTimestamp // SYSDATE로 추가하기 위한 어노테이션
	@Column(name="REG_DATE")
	private Date regDate;
	
	@Column(name="VIEW_CNT")
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
}
