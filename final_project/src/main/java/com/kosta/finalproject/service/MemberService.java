package com.kosta.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.finalproject.dto.MemberDTO;
import com.kosta.finalproject.entity.MemberEntity;
import com.kosta.finalproject.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Long save(MemberDTO memberDTO) {
		
		log.info("1. 암호화 전 비밀번호는 ? : "+memberDTO.getMemberPassword().toString());
		
		// 암호화 모듈로 들어가서 리턴된 값이 String encodePw에 들어감
		String encodePw = passwordEncoder.encode(memberDTO.getMemberPassword());
		
		log.info("2. 암호화 후 비밀번호는 ? : "+encodePw.toString());
		
		memberDTO.setMemberPassword(encodePw);
		
		//MemberEntity memberEntity = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)); 						
		MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
		
		Long savedId = memberRepository.save(memberEntity).getMemberNo();
		return savedId;
		
	}

	public MemberDTO login(MemberDTO memberDTO) {
		
		String pw = memberDTO.getMemberPassword();
		
		/**
		 * login.html에서 아이디, 비번을 받아오고
		 * DB 로부터 해당 이메일의 정보를 가져와서
		 * 입력받은 비번과 DB에서 조회한 비번의 일치여부를 판단하여
		 * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
		 */
		// return false;
		//memberDTO 로그인페이지에서 입력한값
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberDTO.getMemberId());	
		
		
		log.info("optionalMemberEntity >>>>>>>>>>>>>>>>> "+optionalMemberEntity.toString());
		
		log.info("1. 로그인 페이지에서 입력한 비밀번호 값 : "+pw);
	
		if (optionalMemberEntity.isPresent()) {

			//DB에서 조회한 회원정보
			MemberEntity loginEntity = optionalMemberEntity.get();

			log.info("2. 회원 테이블에서 조회한 암호화한 값 : "+loginEntity.getMemberPassword());
			
			//내가 입력한 평문 비밀번호랑  회원정보에있는 비밀번호(암호화한것)을 비교함
			if (passwordEncoder.matches(pw, loginEntity.getMemberPassword())) {
				
				return MemberDTO.toMemberDTO(loginEntity);
			
			} else {
				
				return null;
			}
			
		}else {
			
			return null;
		
		}
	}

	public MemberDTO findById(Long id) {
	 Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
	 if (optionalMemberEntity.isPresent()) {
	//	 return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		 MemberEntity memberEntity = optionalMemberEntity.get();
		 MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
		 return memberDTO;
	 } else {
		 return null;
	 }
		 
 }

public List<MemberDTO> findAll() {
	List<MemberEntity> memberEntityList = memberRepository.findAll();
	List<MemberDTO> memberDTOList = new ArrayList<>();
	for (MemberEntity member: memberEntityList) {
		//MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
		//memberDTOList.add(memberDTO);
		memberDTOList.add(MemberDTO.toMemberDTO(member));
	}
	return memberDTOList;
}

public void delete(Long id) {
	memberRepository.deleteById(id);
	
	
}
	}
