package kr.co.krace.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import kr.co.krace.vo.AbstractVO;
import kr.co.krace.vo.PagenationVO;
import kr.co.krace.vo.SearchVO;
import kr.co.krace.vo.StoreVO;

public class StoreDAO extends BaseDAO {
	
	public PagenationVO selectStoreList(String storeType, String cityName, String searchValue, SearchVO search) {
		PagenationVO pageVO = null;
		SqlMapClientTemplate jt = getSqlMapClientTemplate();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startNumber", search.getStartNumber());
		param.put("rowNum", search.getRowNum());
		
		if(storeType != null && !"".equals(storeType)) {
			param.put("storeType", storeType);			
		}
		if(cityName != null && !"".equals(cityName)) {
			param.put("cityName", cityName+"%");			
		}		
		if(searchValue != null && !"".equals(searchValue)) {
			param.put("searchValue", "%" + searchValue + "%");			
		}
		
		List<AbstractVO> list = jt.queryForList("page.select.storeList", param);
		int totalNum = (Integer)jt.queryForObject("count.select.storeList", param);
		
		pageVO = new PagenationVO();
		pageVO.setPageNum(search.getPageNum());
		pageVO.setRowNum(search.getRowNum());
		pageVO.setTotalCount(totalNum);
		pageVO.setVoList(list);
		
		return pageVO;
	}
	
	public StoreVO selectStore(int storeId) {
		SqlMapClientTemplate jt = getSqlMapClientTemplate();
		return (StoreVO)jt.queryForObject("select.store.by.storeid", storeId);
	}
	
	public int selectStoreListCnt(String storeType, String cityName, String searchValue) {
		SqlMapClientTemplate jt = getSqlMapClientTemplate();
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		if(storeType != null && !"".equals(storeType)) {
			param.put("storeType", storeType);			
		}
		if(cityName != null && !"".equals(cityName)) {
			param.put("cityName", cityName+"%");			
		}		
		if(searchValue != null && !"".equals(searchValue)) {
			param.put("searchValue", "%" + searchValue + "%");			
		}
		
		int totalNum = (Integer)jt.queryForObject("count.select.storeList", param);
		
		return totalNum;
	}
	
	public List<AbstractVO> selectStoreTypeCnt(String cityName, String searchValue) {
		SqlMapClientTemplate jt = getSqlMapClientTemplate();
		
		Map<String, Object> param = new HashMap<String, Object>();

		if(cityName != null && !"".equals(cityName)) {
			param.put("cityName", cityName+"%");			
		}		
		if(searchValue != null && !"".equals(searchValue)) {
			param.put("searchValue", "%" + searchValue + "%");			
		}
		
		List<AbstractVO> list = jt.queryForList("select.storeTypeCnt", param);
		
		return list;
	}
	
	public List<AbstractVO> selectStoreDistanceList() {
		SqlMapClientTemplate jt = getSqlMapClientTemplate();
		
		List<AbstractVO> list = jt.queryForList("select.store.distance");
		
		return list;
	}
}
