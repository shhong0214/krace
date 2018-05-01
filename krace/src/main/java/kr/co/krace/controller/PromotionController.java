package kr.co.krace.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PromotionController {
	
	@RequestMapping(value="/promotionManagement", method=RequestMethod.GET) 
	public ModelAndView promotionManagement() throws Exception {
		return new ModelAndView("promotionManagement");		
	}
	
	@RequestMapping(value="/viewPromotion", method=RequestMethod.GET) 
	public ModelAndView viewPromotion() throws Exception {
		return new ModelAndView("createPromotion");		
	}
	
	@RequestMapping(value="/createPromotion", method=RequestMethod.GET) 
	public ModelAndView createPromotion() throws Exception {
		return new ModelAndView("createPromotion");		
	}
	
	@RequestMapping(value="/modifyPromotion", method=RequestMethod.GET) 
	public ModelAndView modifyPromotion() throws Exception {
		return new ModelAndView("createPromotion");		
	}
	
	@RequestMapping(value="/deletePromotion", method=RequestMethod.GET) 
	public ModelAndView deletePromotion() throws Exception {
		return new ModelAndView("deletePromotion");		
	}
}
