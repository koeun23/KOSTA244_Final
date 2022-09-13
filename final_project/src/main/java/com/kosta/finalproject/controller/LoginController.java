package com.kosta.finalproject.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.kosta.finalproject.entity.MemberEntity;
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

	@Autowired
	PasswordEncoder passwordEncoder;
	
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

	/*아이디 찾기 페이지 이동*/
	@GetMapping("/findIdForm")
	public String findIdForm() {
		return "login/findIdForm";
	}	

	/*아이디 찾기 조회*/
	@PostMapping("/findId")
	@ResponseBody
	public String findId(MemberDTO memberDTO) {
		
		log.info("아이디찾기 조회 findId ");

		String result = "";
		
		MemberDTO resultDTO = memberService.findByMemberEmailAndMemberName(memberDTO);
		
		if(resultDTO != null) {

			result = resultDTO.getMemberId().toString();
		
		}
		
		return result;
		
	}
	
	/*비밀번호 찾기 페이지 이동*/
	@GetMapping("/findPwForm")
	public String findPwForm() {
		return "login/findPwForm";
	}

	/*비밀번호 찾기 조회*/
	@PostMapping("/findPw")
	@ResponseBody
	public String findPw(MemberDTO memberDTO, HttpSession session) {
		
		log.info("비밀번호찾기 조회 findId ");

		String result = "";
		
		MemberDTO resultDTO = memberService.findByMemberIdAndMemberName(memberDTO);
		
		if(resultDTO != null) {

			//랜덤 비밀번호 생성
			
			String tempPw = getRamdomPassword(8);

			log.info("임시비밀번호 값 tempPw : "+tempPw);
			
			// 암호화 모듈로 들어가서 리턴된 값이 String encodePw에 들어감
			String encodePw = passwordEncoder.encode(tempPw);
			
			resultDTO.setMemberPassword(encodePw);
			
			//내가생각한건.. hye0826 회원정보의 비번을 생성한 임시비번으로 업데이트 칠라고했음..
			//하지만 새로운 1줄이 인서트됨, 이슈
			memberService.save(resultDTO);
			
			result = tempPw;
		
		}
		
		return result;
		
	}	

	/*임시비밀번호 발급 로직*/
    public String getRamdomPassword(int size) {
    	
    	char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
}
