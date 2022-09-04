package com.kosta.finalproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity(name="findcar_board")
@Data
@SequenceGenerator(
name="Find_SEQ_GENERATOR",
sequenceName = "FINDCARBOARD_SEQ",
initialValue=1,
allocationSize=1
)
public class FindCarBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "Find_SEQ_GENERATOR"
					)
	private Integer boardnum;
	private String userid;
	private int boardstatus;
	private String boardtitle;
	private String boardcontents;
	
	private String startpoint;
	private String endpoint;
	private String starttime;
	
	private int userlike;
}
