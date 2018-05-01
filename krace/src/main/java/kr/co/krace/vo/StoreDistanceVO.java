package kr.co.krace.vo;


public class StoreDistanceVO extends AbstractVO implements Comparable<StoreDistanceVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int storeId;
	private double latitude;
	private double longitude;
	private double result;
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	public int compareTo(StoreDistanceVO o) {
		if (result > o.getResult())
            return 1;
        else if (result < o.getResult())
            return -1;
        else
            return 0;

	}
	
}
