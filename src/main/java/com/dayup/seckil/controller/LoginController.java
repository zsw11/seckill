package com.dayup.seckil.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;
import com.dayup.seckil.util.UUIDUtil;
import com.dayup.seckil.util.ValidateCode;

@Controller
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	public UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("user",new User());
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute(value="user") @Valid User user, BindingResult bindingResult,HttpSession session, String code, Model model, HttpServletResponse response){
		log.info("username="+user.getUsername()+";password="+user.getPassword());
		if(bindingResult.hasErrors()){
			return "login";
		}
		String sessionCode = (String) session.getAttribute("code");
		if(!StringUtils.equalsIgnoreCase(code, sessionCode)){
			model.addAttribute("message", "验证码不匹配");
			return "login";
		}
		
		UserVO dbUser = userService.getUser(user.getUsername());
		
		if(dbUser != null){
			if(dbUser.getPassword().equals(MD5Util.inputToDb(user.getPassword(), dbUser.getDbflag()))){
				//session.setAttribute("user", dbUser);
				String token = UUIDUtil.getUUID();
				userService.saveUserToRedisByToken(dbUser, token);
				System.out.println("token===== "+token);
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(3600);
				cookie.setPath("/");
				response.addCookie(cookie);
				
				return "redirect:/home";
			}else{
				return "login";
			}
		}else{
			return "login";
		}
		
	}
	
	@RequestMapping(value="/validateCode")
	public String validate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("image/jpeg");
		//禁止图像缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires",0);//在代理服务器端防止缓冲
		
		HttpSession session = request.getSession();
		ValidateCode validateCode = new ValidateCode(120, 34, 5, 100);
		
		session.setAttribute("code", validateCode.getCode());
		validateCode.write(response.getOutputStream());
		return null;
	}
}
