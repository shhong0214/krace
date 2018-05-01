package kr.co.krace.exception;


public class KRaceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int SERVER_ERROR 						= 1001;
	public static final int CHECK_PASSWORD_ERROR				= 1002;
	public static final int NO_SERVICE_ERROR					= 1003;
	public static final int UNREGISTERED_USER_ERROR				= 1004;
	public static final int INPUT_VALIDATE_ERROR				= 1005;
	
	int code;
	String message;
	Object extraInformation;
	
	public KRaceException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	
	public KRaceException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.message = message;
	}
	
	public KRaceException(int code, String message, Object extraInformation) {
		super(message);
		this.code = code;
		this.message = message;
		this.extraInformation = extraInformation; 
	}	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getExtraInformation() {
		return extraInformation;
	}

	public void setExtraInformation(Object extraInformation) {
		this.extraInformation = extraInformation;
	}
}
