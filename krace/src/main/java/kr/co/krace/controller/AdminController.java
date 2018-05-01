package kr.co.krace.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.krace.common.ExceptionLogger;
import kr.co.krace.common.KRaceConstants;
import kr.co.krace.exception.KRaceException;
import kr.co.krace.security.KRaceAuthenticationException;
import kr.co.krace.service.AdminService;
import kr.co.krace.vo.restservice.ResponseVO;

@Controller
public class AdminController extends BaseController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/dataManagement", method=RequestMethod.GET)
	public String raceManagement(HttpServletResponse response) {
		return "admin/dataManagement";
//		if(getSessionUser() == null) 
//			return "redirect:login.do";
//		else
//			return "redirect:/admin/Home.do";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String index(HttpServletResponse response) {
		if(getSessionUser() == null) 
			return "redirect:login.do";
		else
			return "redirect:/admin/Home.do";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		AuthenticationException exception = 
			(AuthenticationException)request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		response.setHeader(KRaceConstants.CAM_CHECK_REDIRECT, "false");
		
		KRaceAuthenticationException mdmAuthException = null;		
		int errorCode = 0;		
		if(exception != null && exception instanceof KRaceAuthenticationException) {
			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			mdmAuthException = (KRaceAuthenticationException)exception;	
			errorCode = mdmAuthException.getCode();
		}
		
		return new ModelAndView("/admin/login")
			.addObject("errorCode", errorCode);
	}
	
	@RequestMapping(value="/adminHome.do", method=RequestMethod.GET)
	public String adminMain() throws KRaceException {
		if(hasAuthority(KRaceConstants.ROLE_ADMIN)) {
			return "redirect:/storeList.do";
		}	
		
		throw new KRaceException(KRaceException.NO_SERVICE_ERROR, "NO_SERVICE_ERROR");
	}
	
	
	@RequestMapping(value="/updateHorseOwner.do", method=RequestMethod.POST)
	public @ResponseBody ResponseVO updateHorseOwner()
	{
		int resultCode = 0;
		String resultMessage = "";
		
		try {
			
			
			
			
			
			
			
//			List<String> list = (List<String>)selectedIdList.getIdList();
//			List<Long> memberIdList = new ArrayList<Long>();
//			for(String s : list) {
//				memberIdList.add(Long.parseLong(s));
//			}
//			
//			if (approve.equals("Y")) {
//				appMemberAuthService.insertAppMemberAuth(Long.parseLong(appId), memberIdList);
//			}
//			else {
//				appMemberAuthService.deleteAppMemberAuth(Long.parseLong(appId), memberIdList);
//			}
			
		} catch (Exception e) {
			ExceptionLogger.logException(logger, e);
			resultCode = KRaceException.SERVER_ERROR;
			resultMessage = e.getMessage();			
		}
		
		return new ResponseVO(Integer.toString(resultCode), resultMessage);		
	}
}
