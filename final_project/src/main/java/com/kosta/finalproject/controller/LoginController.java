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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.finalproject.dto.MemberDTO;
import com.kosta.finalproject.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* @packageName    : com.kosta.finalproject.controller
* @fileName        : MemberController.java
* @author        : Hye
* @date            : 2022.09.09
* @description   : 회원 관리 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2022.09.09        Hye       최초 생성
*/

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")

public class LoginController {
	private final MemberService memberService;
	
	/**
	* @methodName    : loginForm
	* @author        : Hye
	* @date        : 2022.09.09
	* @description   :  로그인 페이지 이동
	* @return
	*/
	@GetMapping("/loginForm")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String login (MemberDTO memberDTO, HttpSession session) {
		
		MemberDTO loginResult = memberService.login(memberDTO);
		
		if (loginResult != null) {
			//로그인정보를 세션에 담는다
			session.setAttribute("loginInfo", loginResult);
		//	memberDTO = (MemberDTO) session.getAttribute("loginInfo");
			
			return "success";	
		}else {
			return "fail";	
		}
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//모든 세션을 삭제
		session.invalidate();
		return "redirect:/home/main";
		
	}	
	
}
