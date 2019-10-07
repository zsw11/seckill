package com.dayup.seckil.controller.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dayup.seckil.VO.UserVO;
import com.dayup.seckil.base.controller.BaseApiController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.base.result.ResultCode;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Util;
import com.dayup.seckil.util.UUIDUtil;

@RestController
public class LoginApiController extends BaseApiController {

	private static Logger log = LoggerFactory.getLogger(LoginApiController.class);
	
	@Autowired
	public UserService userService;
	
//	@ResponseBody
	@RequestMapping(value="/login")
	public Result<Object> login(@ModelAttribute(value="user") @Valid User user, BindingResult bindingResult,HttpSession session, String code, Model model, HttpServletResponse response){
		log.info("username="+user.getUsername()+";password="+user.getPassword());
		if(bindingResult.hasErrors()){
			return Result.failure();	// 500, "error"
		}
//		String sessionCode = (String) session.getAttribute("code");
//		if(!StringUtils.equalsIgnoreCase(code, sessionCode)){
//			model.addAttribute("message", "验证码不匹配");
//			return "login";
//		}
		
		UserVO dbUser = userService.getUser(user.getUsername());
		
		if(dbUser != null){
			if(dbUser.getPassword().equals(MD5Util.inputToDb(user.getPassword(), dbUser.getDbflag()))){
				//session.setAttribute("user", dbUser);
				String token = UUIDUtil.getUUID();
				userService.saveUserToRedisByToken(dbUser, token);
				
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(3600);
				cookie.setPath("/");
				response.addCookie(cookie);
				
				return Result.success();	// Result.success(); 200, "success"
			}else{
				return Result.failure(ResultCode.USER_LOGIN_ERROR);
			}
		}else{
			return Result.failure(ResultCode.USER_LOGIN_ERROR);
		}
		
	}
	
//	@RequestMapping(value="/validateCode")
//	public String validate(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		response.setContentType("image/jpeg");
//		//禁止图像缓存
//		response.setHeader("Pragma", "No-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires",0);//在代理服务器端防止缓冲
//		
//		HttpSession session = request.getSession();
//		ValidateCode validateCode = new ValidateCode(120, 34, 5, 100);
//		
//		session.setAttribute("code", validateCode.getCode());
//		validateCode.write(response.getOutputStream());
//		return null;
//	}
}
