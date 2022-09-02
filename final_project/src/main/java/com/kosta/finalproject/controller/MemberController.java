package com.kosta.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
