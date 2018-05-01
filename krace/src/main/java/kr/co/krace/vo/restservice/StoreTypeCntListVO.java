package kr.co.krace.vo.restservice;


import java.util.List;

import kr.co.krace.vo.StoreTypeCntVO;

public class StoreTypeCntListVO {
	int storeAllCnt;
	List<StoreTypeCntVO> storeTypeCntList;
	
	public StoreTypeCntListVO() {
		super();
	}
	
	public StoreTypeCntListVO(List<StoreTypeCntVO> storeTypeCntList, int storeAllCnt) {
		super();
		this.storeTypeCntList = storeTypeCntList;
		this.storeAllCnt = storeAllCnt;
	}

	public int getStoreAllCnt() {
		return storeAllCnt;
	}

	public void setStoreAllCnt(int storeAllCnt) {
		this.storeAllCnt = storeAllCnt;
	}

//	@XmlElement(name = "storeType")	
	public List<StoreTypeCntVO> getStoreTypeCntList() {
		return storeTypeCntList;
	}

	public void setStoreTypeCntList(List<StoreTypeCntVO> storeTypeCntList) {
		this.storeTypeCntList = storeTypeCntList;
	}

	
	
}
