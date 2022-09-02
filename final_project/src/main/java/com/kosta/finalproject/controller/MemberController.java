package com.kosta.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.finalproject.dto.MemberDTO;
import com.kosta.finalproject.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")

public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/save-form")
	public String saveForm() {
		return "memberPages/save";
	}
	@GetMapping("/login-form")
	public String loginForm() {
		return "memberPages/login";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute MemberDTO memberDTO) {
		memberService.save(memberDTO);
		return "memberPages/login";
	}
	
	@PostMapping("/login")
	public String login (@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.login(memberDTO);
		if (loginResult != null) {
			session.setAttribute("loginEmail", loginResult.getMemberEmail());
			session.setAttribute("id", loginResult.getId());
			return "memberPages/main";	
		}else {
			return "memberPages/login";
		}
		
	}
	@GetMapping("/")
	public String findAll(Model model) {
		List<MemberDTO> memberDTOList =memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "memberPages/list";
		
	}
	// /member/3
	//  /member?id=3
	@GetMapping("/{id}")
	public String findById(@PathVariable Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member",memberDTO);
		return "memberPages/detail";
		
		
	}
	//ajax상세 조회
	@PostMapping("/ajax/{id}")
	public @ResponseBody MemberDTO findByIdAjax(@PathVariable Long id) {
		MemberDTO memberDTO = memberService.findById(id);
		return memberDTO;
	}
	
	//get 요청 삭제 /member/delete/3
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id ) {
		memberService.delete(id);
		return "redirect:/member/";
	//	return"memberPages.list";//X
	}
	
	 /**
	  * /member/3: 조회(get) R, 저장(post) C, 수정(put) U, 삭제(delete)  D
	  */
	
	
	
		//delete요청 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity deleteAjax(@PathVariable Long id) {
		memberService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK); //ajax 호출한 부분에 리턴으로 200 응답을 줌.
		
	}
	}
