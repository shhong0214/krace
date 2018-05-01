package kr.co.krace.vo;


public class SearchVO extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int pageNum;
	private int rowNum;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getStartNumber() {
		return (getPageNum()-1)*getRowNum();
	}

	public int getEndNumber() {
		return (getPageNum()-1)*getRowNum()+getRowNum();
	}

}
