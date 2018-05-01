package kr.co.krace.vo;


import java.util.Date;

public class MemberVO extends AbstractVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long memberId;
	String memberName;
	String password;
	String passwordEncoding;	
	String memberType;	
	Date createDate;
	Date modifyDate;
	String deleted;
	
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordEncoding() {
		return passwordEncoding;
	}
	public void setPasswordEncoding(String passwordEncoding) {
		this.passwordEncoding = passwordEncoding;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
}
