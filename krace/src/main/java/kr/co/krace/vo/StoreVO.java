package kr.co.krace.vo;


import kr.co.krace.common.KRaceConstants;

public class StoreVO extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int storeId;
	private String storeType;
	private String storeTypeStr;
	private String storeName;
	private String storeAddress;
	private String storeTel;
	private String deleted;
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getStoreTypeStr() {
		String str = "백화점";
		
		if (getStoreType().equals(KRaceConstants.STORE_TYPE_DIRECT_MANAGEMENT_STORE))
			str = "직영점";
		
		if (getStoreType().equals(KRaceConstants.STORE_TYPE_DISCOUNT_AGENT))
			str = "상설대리점";
		
		if (getStoreType().equals(KRaceConstants.STORE_TYPE_OUTLET))
			str = "아울렛";
		
		if (getStoreType().equals(KRaceConstants.STORE_TYPE_AGENT))
			str = "정상대리점";
		
		return str;
	}
	public void setStoreTypeStr(String storeTypeStr) {
		this.storeTypeStr = storeTypeStr;
	}
	
	
}
