package kr.co.krace.vo.restservice;


import java.util.List;

import kr.co.krace.vo.StoreVO;


public class StoreListVO {
	int count;
	int totalPageCount;
	List<StoreVO> storeList;
	
	public StoreListVO() {
		super();
	}
	
	public StoreListVO(List<StoreVO> storeList) {
		super();
		this.storeList = storeList;
		this.count = storeList.size();		
	}

	public StoreListVO(List<StoreVO> storeList, int totalPageCount) {
		super();
		this.storeList = storeList;
		this.count = storeList.size();		
		this.totalPageCount = totalPageCount;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

//	@XmlElement(name = "store")	
	public List<StoreVO> getStore() {
		return storeList;
	}

	public void setStore(List<StoreVO> storeList) {
		this.storeList = storeList;
	}
}
