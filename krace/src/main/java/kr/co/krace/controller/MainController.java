package kr.co.krace.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpServletRequest request,  HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value="/index.html", method=RequestMethod.GET)
	public String indexPage(HttpServletRequest request,  HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String indexDoPage(HttpServletRequest request,  HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value="/main.do", method=RequestMethod.GET)
	public String main(HttpServletRequest request,  HttpServletResponse response) {
		return "index";
	}
	
	@RequestMapping(value="/history.do", method=RequestMethod.GET)
	public String history(HttpServletRequest request, HttpServletResponse response) {
		return "history/history";
	}
	
	@RequestMapping(value="/pop_promo.do", method=RequestMethod.GET)
	public String pop_promo(HttpServletRequest request,  HttpServletResponse response) {
		return "pop_promo";
	}
	
	@RequestMapping(value="/mtmDetail.do", method=RequestMethod.GET)
	public String mtmDetail(HttpServletRequest request,  HttpServletResponse response) {
		return "mtm/detail";
	}
}
