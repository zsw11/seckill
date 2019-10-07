package com.dayup.seckil.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;

@Controller
public class RegisterController {

	private static Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	public UserService userService;
	
	@RequestMapping(value="/reg", method=RequestMethod.GET)
	public String toRegister(Model model){
		model.addAttribute("user",new User());
		return "regiter";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute(value="user") @Valid User user, BindingResult bindingResult){
		log.info("username="+user.getUsername()+";password="+user.getPassword());
		if(bindingResult.hasErrors()){
			return new ModelAndView("regiter");
		}
		String salt = "alex";
		String newPassword = MD5Util.backtToDb(user.getPassword(), salt);
		user.setId(2018);
		user.setPassword(newPassword);
		user.setDbflag(salt);
		userService.regist(user);
		return new ModelAndView("regiter");
		
	}
}
