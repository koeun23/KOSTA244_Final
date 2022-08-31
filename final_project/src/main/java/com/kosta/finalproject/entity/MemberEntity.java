package com.kosta.finalproject.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kosta.finalproject.dto.MemberDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "member_table")
public class MemberEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(length = 50, unique = true)
	private String memberEmail;
	
	@Column(length = 20)
	private String memberPassword;
	
	@Column(length = 20)
	private String memberName;
	
	@Column(length = 20)
	private Date memberBirth;
	
	@Column(length = 30)
	private String memberMobile;
	
	@Column(length = 30)
	private String memberLicense;
	
	@Column(length = 10)
	private int memberLike;
	
	public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		memberEntity.setMemberPassword(memberDTO.getMemberEmail());
		memberEntity.setMemberName(memberDTO.getMemberName());
		memberEntity.setMemberBirth(memberDTO.getMemberBirth());
		memberEntity.setMemberMobile(memberDTO.getMemberMobile());
		memberEntity.setMemberLicense(memberDTO.getMemberLicense());
		memberEntity.setMemberLike(memberDTO.getMemberLike());
		return memberEntity;
		
	
}
	
}


