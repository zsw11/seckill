package com.dayup.seckil.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dayup.seckil.model.User;
import com.dayup.seckil.service.UserService;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

	@Autowired
	public UserService userService;

	public String getParameterCokies(HttpServletRequest request,String tokenName){
		Cookie[] cookies = request.getCookies();
		for(Cookie ck : cookies){
			if(ck.getName().equals(tokenName)){
				return ck.getValue();
			}
		}
		return null;
	}

	
	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
		String requestParameter_token = request.getParameter("token");
		String Cokies_token = getParameterCokies(request,"token");
		if(requestParameter_token == null && Cokies_token == null){
			return null;
		}
		return userService.getUserFromRedisByToken((requestParameter_token != null ? requestParameter_token : Cokies_token));
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		Class<?> p_class = methodParameter.getParameterType();
		return p_class == User.class;
	}


}
