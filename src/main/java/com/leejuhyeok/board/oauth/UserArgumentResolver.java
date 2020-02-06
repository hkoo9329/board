package com.leejuhyeok.board.oauth;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leejuhyeok.board.annotation.Socialuser;
import com.leejuhyeok.board.domain.SessionUser;
import com.leejuhyeok.board.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver{
	
	private final HttpSession httpSession;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(Socialuser.class) != null;
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		return isLoginUserAnnotation && isUserClass;
	}

	@Override
	public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		return (User)httpSession.getAttribute("user");
	}

}
