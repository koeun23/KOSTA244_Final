package com.kosta.finalproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity(name="BOARD")
@Data
@SequenceGenerator(
name="BOARD_SEQ_GENERATOR",
sequenceName = "BOARD_SEQ",
initialValue=1,
allocationSize=1
)

public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "BOARD_SEQ_GENERATOR"
					)
	@Column(name="BOARD_NO")
	private Long boardNo;
	
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="BOARD_STATUS")
	private int boardStatus;
	
	@Column(name="BOARD_TITLE")
	private String boardTitle;
	
	@Column(name="BOARD_STARTPOINT")
	private String boardStartoint;
	
	@Column(name="BOARD_ENDPOINT")
	private String boardEndpoint;
	
	@Column(name="BOARD_STARTTIME")
	private Date boardStarttime;
	
	@Column(name="BOARD_CONTENTS")
	private String boardContents;
	
}
