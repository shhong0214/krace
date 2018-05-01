package kr.co.krace.vo;


import java.util.List;

public class PagenationVO extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalCount;
	private int rowNum;
	private int pageNum;
	private List<AbstractVO> voList;
	private AbstractVO vo;

	public AbstractVO getVo() {
		return vo;
	}
	public void setVo(AbstractVO vo) {
		this.vo = vo;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalNum) {
		this.totalCount = totalNum;
	}
	public List<AbstractVO> getVoList() {
		return voList;
	}
	public void setVoList(List<AbstractVO> list) {
		this.voList = list;
	}
}
