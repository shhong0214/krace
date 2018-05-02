package kr.co.krace.vo;

import java.util.ArrayList;
import java.util.Date;


public class HorseOwnerVO extends AbstractVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;					// ID
	private String name;				// 마주명
	private int addHorseCnt;			// 총등록말
	private int delHorseCnt;			// 총취소말
	private int totalHorseCnt;			// 현소유말
	private Date regDate;				// 마주등록일
	private String newRecord;			// 최근1년전적
	private String newMoney;			// 최근1년상금
	private String totalRecord;			// 통산전적
	private String totlaMoney;			// 통산상금
	private String clothColor;			// 마주복색
	private String meet;				// 지역
	
	private ArrayList<HorseOwnerOwnVO> ownList;		// 마주 소유말 현황
	private ArrayList<HorseOwnerVictoryVO> victoryList;	// 마주 대상경주 우승전적
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAddHorseCnt() {
		return addHorseCnt;
	}
	public void setAddHorseCnt(int addHorseCnt) {
		this.addHorseCnt = addHorseCnt;
	}
	public int getDelHorseCnt() {
		return delHorseCnt;
	}
	public void setDelHorseCnt(int delHorseCnt) {
		this.delHorseCnt = delHorseCnt;
	}
	public int getTotalHorseCnt() {
		return totalHorseCnt;
	}
	public void setTotalHorseCnt(int totalHorseCnt) {
		this.totalHorseCnt = totalHorseCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getNewRecord() {
		return newRecord;
	}
	public void setNewRecord(String newRecord) {
		this.newRecord = newRecord;
	}
	public String getNewMoney() {
		return newMoney;
	}
	public void setNewMoney(String newMoney) {
		this.newMoney = newMoney;
	}
	public String getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public String getTotlaMoney() {
		return totlaMoney;
	}
	public void setTotlaMoney(String totlaMoney) {
		this.totlaMoney = totlaMoney;
	}
	public String getClothColor() {
		return clothColor;
	}
	public void setClothColor(String clothColor) {
		this.clothColor = clothColor;
	}
	public String getMeet() {
		return meet;
	}
	public void setMeet(String meet) {
		this.meet = meet;
	}
	public ArrayList<HorseOwnerOwnVO> getOwnList() {
		return ownList;
	}
	public void setOwnList(ArrayList<HorseOwnerOwnVO> ownList) {
		this.ownList = ownList;
	}
	public ArrayList<HorseOwnerVictoryVO> getVictoryList() {
		return victoryList;
	}
	public void setVictoryList(ArrayList<HorseOwnerVictoryVO> victoryList) {
		this.victoryList = victoryList;
	}
	
}

