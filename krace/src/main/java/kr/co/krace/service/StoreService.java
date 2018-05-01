package kr.co.krace.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.krace.dao.StoreDAO;
import kr.co.krace.vo.AbstractVO;
import kr.co.krace.vo.PagenationVO;
import kr.co.krace.vo.SearchVO;
import kr.co.krace.vo.StoreVO;


@Service
public class StoreService {

	@Autowired
	StoreDAO storeDAO;
	
	public PagenationVO getShopList(String storeType, String cityName, String searchValue, int pageNum, int rowNum) {
		SearchVO search = new SearchVO();
		search.setPageNum(pageNum);
		search.setRowNum(rowNum);
		
		return storeDAO.selectStoreList(storeType, cityName, searchValue, search);
	}
	
	public int getStoreListCnt(String storeType, String cityName, String searchValue) {
		return storeDAO.selectStoreListCnt(storeType, cityName, searchValue);
	}
	
	public StoreVO getStore(int storeId) {
		return storeDAO.selectStore(storeId);
	}
	
	public List<AbstractVO> getStoreTypeCnt(String cityName, String searchValue){
		return storeDAO.selectStoreTypeCnt(cityName, searchValue);
	}
	
	public List<AbstractVO> getStoreDistanceList(){
		return storeDAO.selectStoreDistanceList();
	}
}
