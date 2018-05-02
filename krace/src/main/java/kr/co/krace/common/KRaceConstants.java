package kr.co.krace.common;



public interface KRaceConstants {
	
	public static final int DEFAULT_ROW_NUM = 20;
	
	public static final String RESPONSE_SUCCESSS = "SUCCESS";
	public static final String RESPONSE_SERVER_ERROR = "SERVER_ERROR";
	
	// 백화점
	public static final String STORE_TYPE_DEPARTMENT = "DEPARTMENT";
	
	// 직영점
	public static final String STORE_TYPE_DIRECT_MANAGEMENT_STORE = "DIRECT_MANAGEMENT_STORE";
	
	// 아울렛
	public static final String STORE_TYPE_OUTLET = "OUTLET";

	// 정상 대리점
	public static final String STORE_TYPE_AGENT = "AGENT";
	
	// 상설 대리점
	public static final String STORE_TYPE_DISCOUNT_AGENT = "DISCOUNT_AGENT";
	
	
	// Check Redirect
	public static final String CAM_CHECK_REDIRECT = "cam-check-redirect";
	
	// Member
	public static final String MEMBER_TYPE_ADMIN = "A";			// 관리자
	public static final String MEMBER_TYPE_USER = "U";			// 사용자
	public static final String MEMBER_TYPE_EXPERT = "P";		// 전문가
	public static final String MEMBER_TYPE_SYSTEM = "S";		// 시스템

	public static final String ROLE_ADMIN ="ROLE_ADMIN";
	public static final String ROLE_USER ="ROLE_USER";

	
	public static final String KRA_URL = "http://race.kra.co.kr";
	
	public static final String UPDATE_HORSEOWNER_LIST_URL = KRA_URL + "/owner/posessStateHorseownerList.do";
	public static final String UPDATE_HORSEOWNER_DETAIL_URL = KRA_URL + "/owner/posessStateSoyuHorsesState.do";
	
	
	
}
