package kr.co.krace.vo.restservice;


public class ResponseHeadVO {
	String resultCode;
	String resultMessage;
	
	public ResponseHeadVO() {
		
	}
	
	public ResponseHeadVO(String resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}

	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getResultMessage() {
		return resultMessage;
	}
	
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}
