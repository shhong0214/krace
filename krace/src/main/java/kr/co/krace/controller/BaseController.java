package kr.co.krace.controller;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import kr.co.krace.common.KRaceConstants;
import kr.co.krace.exception.KRaceException;
import kr.co.krace.security.SaltedUser;

public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	protected SaltedUser getSessionUser() {
		SaltedUser user = null;
		
		Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof SaltedUser) {
			user = (SaltedUser)principal;
		}
		
		return user;
	}
	
	protected boolean hasAuthority(String role) {
		for(GrantedAuthority authority : getAuthentication().getAuthorities()) {
			if(authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletResponse response) {
		KRaceException mdmException = new KRaceException(KRaceException.SERVER_ERROR, ex.toString());
		response.setHeader(KRaceConstants.CAM_CHECK_REDIRECT, "false");
		return new ModelAndView("error").addObject("exception", mdmException);
	}	
	
	@ExceptionHandler(KRaceException.class)
	public ModelAndView handleMdmException(KRaceException ex, HttpServletResponse response) {
		response.setHeader(KRaceConstants.CAM_CHECK_REDIRECT, "false");
		return new ModelAndView("error").addObject("exception", ex);
	}	
}
