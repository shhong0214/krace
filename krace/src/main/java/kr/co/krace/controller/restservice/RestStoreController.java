package kr.co.krace.controller.restservice;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhncorp.lucy.security.xss.XssSaxFilter;

import kr.co.krace.common.KRaceConstants;
import kr.co.krace.exception.KRaceException;
import kr.co.krace.service.StoreService;
import kr.co.krace.vo.AbstractVO;
import kr.co.krace.vo.PagenationVO;
import kr.co.krace.vo.StoreTypeCntVO;
import kr.co.krace.vo.StoreVO;
import kr.co.krace.vo.restservice.ResponseVO;
import kr.co.krace.vo.restservice.StoreListVO;
import kr.co.krace.vo.restservice.StoreTypeCntListVO;

@Controller
@RequestMapping("/restservice/store")
public class RestStoreController extends RestServiceBaseController {
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value = "/storelist.do",
			method = RequestMethod.POST)
	public @ResponseBody ResponseVO getStoreList(HttpServletRequest req, HttpServletResponse res) {
		int resultCode = 0;
		String resultMessage = KRaceConstants.RESPONSE_SUCCESSS;	
		
		int totalPageCount = 0;
		List<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			String storeType = req.getParameter("storeType");
			String cityName = req.getParameter("cityName");
			String searchValue = req.getParameter("searchValue");
			
			XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
			searchValue = filter.doFilter(searchValue);
			
			// 잘못된 입력 값 체크
			if (!Pattern.matches("^[ㄱ-ㅎ가-힣0-9A-Za-z]*$", searchValue)) {
				searchValue = "";
				resultCode = KRaceException.INPUT_VALIDATE_ERROR;
				resultMessage = KRaceConstants.RESPONSE_SERVER_ERROR;	
			}
			
			String pageNum = req.getParameter("pageNum");
			
			PagenationVO pageVO = storeService.getShopList(storeType, cityName, searchValue, Integer.parseInt(pageNum), KRaceConstants.DEFAULT_ROW_NUM);

			totalPageCount = (pageVO.getTotalCount()%KRaceConstants.DEFAULT_ROW_NUM == 0) ? 
						pageVO.getTotalCount()/KRaceConstants.DEFAULT_ROW_NUM : pageVO.getTotalCount()/KRaceConstants.DEFAULT_ROW_NUM+1;
			
			if(totalPageCount == 0){
				totalPageCount = 1;		
			}
			
			for(AbstractVO vo : pageVO.getVoList()) {
				list.add((StoreVO)vo);
			}
			
		} catch (Exception e) {
			resultCode = KRaceException.SERVER_ERROR;
			resultMessage = KRaceConstants.RESPONSE_SERVER_ERROR;	
		}
		
		StoreListVO storeList = new StoreListVO(list, totalPageCount);
		
		ResponseVO response = new ResponseVO(Integer.toString(resultCode), resultMessage, storeList);		
		return response;
	}
	
	@RequestMapping(value = "/nearstorelist.do",
			method = RequestMethod.POST)
	public @ResponseBody ResponseVO getNearStoreList(HttpServletRequest req, HttpServletResponse res) {
		int resultCode = 0;
		String resultMessage = KRaceConstants.RESPONSE_SUCCESSS;	
		
		int totalPageCount = 0;
		List<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			String searchValue = req.getParameter("searchValue");
			
			searchValue = convertCityName(searchValue);
			
			XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
			searchValue = filter.doFilter(searchValue);

			
			PagenationVO pageVO = storeService.getShopList("", "", searchValue, 1, KRaceConstants.DEFAULT_ROW_NUM);

			totalPageCount = (pageVO.getTotalCount()%KRaceConstants.DEFAULT_ROW_NUM == 0) ? 
						pageVO.getTotalCount()/KRaceConstants.DEFAULT_ROW_NUM : pageVO.getTotalCount()/KRaceConstants.DEFAULT_ROW_NUM+1;
			
			if(totalPageCount == 0){
				totalPageCount = 1;		
			}
			
			for(AbstractVO vo : pageVO.getVoList()) {
				list.add((StoreVO)vo);
			}
			
		} catch (Exception e) {
			resultCode = KRaceException.SERVER_ERROR;
			resultMessage = KRaceConstants.RESPONSE_SERVER_ERROR;	
		}
		
		StoreListVO storeList = new StoreListVO(list, totalPageCount);
		
		ResponseVO response = new ResponseVO(Integer.toString(resultCode), resultMessage, storeList);		
		return response;
	}
	
	
	private String convertCityName(String cityName) {
		cityName = cityName.replaceFirst("특별시", "");
		cityName = cityName.replaceFirst("광역시", "");
		
		if (cityName.startsWith("경기도")  || cityName.startsWith("강원도") || cityName.startsWith("제주도")) {
			cityName = cityName.substring(0, 2);
		}
		
		if (cityName.startsWith("경상북도")  || cityName.startsWith("경상남도") || cityName.startsWith("전라남도")
				 || cityName.startsWith("전라북도") || cityName.startsWith("충청남도") || cityName.startsWith("충청북도")) {
			cityName = cityName.substring(0, 1) + cityName.substring(2, 3);
		}
		
		String[] split = cityName.split(" ");
		if (split.length > 0) {
			cityName = split[0];
		}
		
		return cityName;
	}
	
	@RequestMapping(value = "/storeTypeCnt.do",
			method = RequestMethod.POST)
	public @ResponseBody ResponseVO getStoreTypeCnt(HttpServletRequest req, HttpServletResponse res) {
		int resultCode = 0;
		String resultMessage = KRaceConstants.RESPONSE_SUCCESSS;	
		
		int storeAllCnt = 0; 
		
		List<StoreTypeCntVO> list = new ArrayList<StoreTypeCntVO>();
		
		try {
			String cityName = req.getParameter("cityName");
			String searchValue = req.getParameter("searchValue");
			
			XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml");
			searchValue = filter.doFilter(searchValue);
			
			// 잘못된 입력 값 체크
			if (!Pattern.matches("^[ㄱ-ㅎ가-힣0-9A-Za-z]*$", searchValue)) {
				searchValue = "";
				resultCode = KRaceException.INPUT_VALIDATE_ERROR;
				resultMessage = KRaceConstants.RESPONSE_SERVER_ERROR;	
			}
			
			for (AbstractVO vo : storeService.getStoreTypeCnt(cityName, searchValue)) {
				StoreTypeCntVO cntVO = (StoreTypeCntVO)vo;
				list.add(cntVO);
				
				storeAllCnt += cntVO.getCnt();
			}
			
		} catch (Exception e) {
			resultCode = KRaceException.SERVER_ERROR;
			resultMessage = KRaceConstants.RESPONSE_SERVER_ERROR;	
		}
		
		StoreTypeCntListVO storeTypeCntList = new StoreTypeCntListVO(list, storeAllCnt);
		
		ResponseVO response = new ResponseVO(Integer.toString(resultCode), resultMessage, storeTypeCntList);		
		return response;
	}
	
}
