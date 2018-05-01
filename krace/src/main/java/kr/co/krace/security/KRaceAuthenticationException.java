package kr.co.krace.security;


import org.springframework.security.core.AuthenticationException;

public class KRaceAuthenticationException extends AuthenticationException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	
	public KRaceAuthenticationException(int code, String msg, Object extraInformation) {
		super(msg, extraInformation);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}
