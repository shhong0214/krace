package kr.co.krace.vo.restservice;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class ResponseVO {
	ResponseHeadVO responseHead;
	ResponseDataVO responseData;
	
	public ResponseVO() {
		
	}
	public ResponseVO(String resultCode, String resultMessage, Object responseDataObject) {
		super();		
		this.responseHead = new ResponseHeadVO(resultCode, resultMessage);
		this.responseData = new ResponseDataVO(responseDataObject);
	}	
	
	public ResponseVO(String resultCode, String resultMessage) {
		super();		
		this.responseHead = new ResponseHeadVO(resultCode, resultMessage);
	}

	public ResponseHeadVO getResponseHead() {
		return responseHead;
	}
	
	public void setResponseHead(ResponseHeadVO responseHead) {
		this.responseHead = responseHead;
	}
	
	public ResponseDataVO getResponseData() {
		return responseData;
	}
	
	public void setResponseData(ResponseDataVO responseData) {
		this.responseData = responseData;
	}


}
