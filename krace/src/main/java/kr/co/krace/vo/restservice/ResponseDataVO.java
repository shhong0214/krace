package kr.co.krace.vo.restservice;




public class ResponseDataVO {
	StoreListVO storeList;
	StoreTypeCntListVO storeCntList;

	public ResponseDataVO() {
		
	}
	
	public ResponseDataVO(Object responseDataObject) {
		if(responseDataObject instanceof StoreListVO ) {
			this.storeList = (StoreListVO)responseDataObject;			
		} 
		else if(responseDataObject instanceof StoreTypeCntListVO ) {
			this.storeCntList = (StoreTypeCntListVO)responseDataObject;			
		} 
	}

	public StoreListVO getStoreList() {
		return storeList;
	}

	public void setStoreList(StoreListVO storeList) {
		this.storeList = storeList;
	}

	public StoreTypeCntListVO getStoreCntList() {
		return storeCntList;
	}

	public void setStoreCntList(StoreTypeCntListVO storeCntList) {
		this.storeCntList = storeCntList;
	}
	
}
