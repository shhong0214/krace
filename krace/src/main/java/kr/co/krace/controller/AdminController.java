package kr.co.krace.controller;



import java.util.ArrayList;

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
import kr.co.krace.service.HorseOwnerOwnService;
import kr.co.krace.service.HorseOwnerService;
import kr.co.krace.service.HorseOwnerVictoryService;
import kr.co.krace.service.TrainerService;
import kr.co.krace.vo.HorseOwnerOwnVO;
import kr.co.krace.vo.HorseOwnerVO;
import kr.co.krace.vo.HorseOwnerVictoryVO;
import kr.co.krace.vo.TrainerVO;
import kr.co.krace.vo.restservice.ResponseVO;

@Controller
public class AdminController extends BaseController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	HorseOwnerService ownerService;
	
	@Autowired
	HorseOwnerOwnService ownerOwnService;
	
	@Autowired
	HorseOwnerVictoryService ownerVictoryService;
	
	@Autowired
	TrainerService trainerService;
	
	
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
	public @ResponseBody ResponseVO updateHorseOwner(HttpServletRequest request)
	{
		int resultCode = 0;
		String resultMessage = "";
		
		for (int i=1; i<4; i++) {
			
			if (i == 1) 
				logger.debug("서울 마주정보 업데이트 시작");
			if (i == 2)
				logger.debug("제주 마주정보 업데이트 시작");
			if (i == 3)
				logger.debug("부산경남 마주정보 업데이트 시작");
			
			try {
				ArrayList<HorseOwnerVO> list = adminService.getHorseOwnerList(String.valueOf(i));
				
				ownerService.deleteHorseOwner(String.valueOf(i));
				
				for (HorseOwnerVO vo : list) {
					ownerService.insertHorseOwner(vo);
					
					// 소유 현황
					ownerOwnService.deleteHorseOwnerOwn(vo.getId());
					ArrayList<HorseOwnerOwnVO> ownlist = vo.getOwnList();
					
					if (ownlist != null) {
						for (HorseOwnerOwnVO own : ownlist) {
							if (own != null)
								ownerOwnService.insertHorseOwnerOwn(own);
						}
					}
					
					// 우승 현황
					ownerVictoryService.deleteHorseOwnerVictory(vo.getId());
					ArrayList<HorseOwnerVictoryVO> victoryList = vo.getVictoryList();
					
					if (victoryList != null) {
						for (HorseOwnerVictoryVO victory : victoryList) {
							if (victory != null)
								ownerVictoryService.insertHorseOwnerVictory(victory);
						}
					}
					
				}
				
			} catch (Exception e) {
				ExceptionLogger.logException(logger, e);
				resultCode = KRaceException.SERVER_ERROR;
				resultMessage = e.getMessage();			
			}
		
		}
		
		return new ResponseVO(Integer.toString(resultCode), resultMessage);		
	}
	
	
	@RequestMapping(value="/updateTrainer.do", method=RequestMethod.POST)
	public @ResponseBody ResponseVO updateTrainer(HttpServletRequest request)
	{
		int resultCode = 0;
		String resultMessage = "";
		
		for (int i=1; i<4; i++) {
			
			if (i == 1) 
				logger.debug("서울 마주정보 업데이트 시작");
			if (i == 2)
				logger.debug("제주 마주정보 업데이트 시작");
			if (i == 3)
				logger.debug("부산경남 마주정보 업데이트 시작");
			
		
			try {
				ArrayList<TrainerVO> list = adminService.getTrainerList(String.valueOf(i));
				
				trainerService.deleteTrainer(String.valueOf(i));
				
				for (TrainerVO trainer : list) {
					trainerService.insertTrainer(trainer);
					
					System.out.println(trainer.getName());
					
				}
				
			} catch (Exception e) {
				ExceptionLogger.logException(logger, e);
				resultCode = KRaceException.SERVER_ERROR;
				resultMessage = e.getMessage();			
			}
		
		}
		
		return new ResponseVO(Integer.toString(resultCode), resultMessage);		
	}
	
}
