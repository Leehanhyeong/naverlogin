package com.inc.test;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.service.LogInterface;

@Controller
public class NaverLoginController {
	
	private String state;
	private String code;
	private String access_token;
	private String refresh_token;
	private String token_type;
	private final String naverClientId ="sPNDNbamFKNSyeyrTtkQ"; //상수 변함x
	private final String naverClientSecret ="8RuSsyflU8";  //""
	private String returnUrl;
	@Autowired
	private LogInterface loginService;  //interface
	
	//@Autowired
	//MemberDAO  등이 들어와야 함.
	
	@Autowired(required = true) //무조건 들어와야 함.
	private HttpServletRequest request;
	
	@RequestMapping("/naverLogin.inc")
	public String makeNaverRequestStatement() { //로고이미지 클릭시
		HashMap<String, String> naverUrl = loginService.makeNaverRequestStatement(
											naverClientId);
				
		state = (String)naverUrl.get("state");
		returnUrl = request.getHeader("referer");
		
		return "redirect:"+(String)naverUrl.get("url");
				
	}
	@RequestMapping("/naverRequestKey.inc")//param중에 state로 넘어오는애가 있다면 state에담아
	public String getNaverRequestKey(@RequestParam("state") String state,
										String code) {
		
		HttpSession session = request.getSession();
		if(state.equals(this.state))
			session.setAttribute("isLoged", Boolean.valueOf(true));//boolean형으로 들어간다.
		else { //그게아니라면
			session.setAttribute("isLoged", Boolean.valueOf(false));
			return "redirect:/";
		}
		this.code = code;
		HashMap<String, String> result = loginService.getNaverRequestKey(
											state, code ,naverClientId ,naverClientSecret);
		
		access_token = (String)result.get("access_token");
		refresh_token = (String)result.get("refresh_token");
		token_type = (String)result.get("token_type");
		
		return "redirect:/naverRequestUserInfo.inc";
		
	}
	//DB에 저장을하는
	@RequestMapping("/naverRequestUserInfo.inc")
	public String naverRequestUserInfo() {
		HashMap<String, String> result = loginService.naverRequestUserInfo(
										token_type, access_token);
		//멤버 vo객체 생성
		
		//이메일, 아이디, 생일, 성별  등을 vo에 저장
		
		//dao를 통해 멤버 추가
		
		//세션에 로그인 정보 저장
		
		
		return "redirect:/";
	}
	
	
	//삭제
	@RequestMapping("/naverLogout.inc") 
	public String naverLogout() {
		HttpSession session = request.getSession();
		// session.removeAttribute("mvo"); 삭제
		
		return "redirect:/";
	}
}












