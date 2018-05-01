package kr.co.krace.controller;


import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nhncorp.lucy.security.xss.XssSaxFilter;

import kr.co.krace.exception.KRaceException;
import kr.co.krace.service.StoreService;


@Controller
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@Value("${daum.map.key}") 
	 String mapKey; 
	
	@RequestMapping(value="storeList.do", method=RequestMethod.GET) 
	public ModelAndView storeList(HttpServletRequest request) throws Exception {
		return new ModelAndView("shop/store_list")
						.addObject("mapKey", mapKey);	
	}
	
	@RequestMapping(value="searchStore.do", method=RequestMethod.POST) 
	public ModelAndView searchStore(HttpServletRequest request) throws Exception {
		String searchValue = request.getParameter("searchValue") == null ? "":request.getParameter("searchValue");
		int resultCode = 0;
		
		XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
		searchValue = filter.doFilter(searchValue);
		
		// 잘못된 입력 값 체크
		if (!Pattern.matches("^[ㄱ-ㅎ가-힣0-9A-Za-z]*$", searchValue)) {
			searchValue = "";
			resultCode = KRaceException.INPUT_VALIDATE_ERROR;
		}
		
		return new ModelAndView("shop/store_list")
						.addObject("mapKey", mapKey)
						.addObject("searchValue", searchValue)
						.addObject("resultCode", String.valueOf(resultCode));
	}
}
