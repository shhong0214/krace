package kr.co.krace.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class KRaceAuthenticationToken  extends UsernamePasswordAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean adminMode;
	
	public KRaceAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	public KRaceAuthenticationToken(Object principal,
			Object credentials, boolean adminMode) {
		super(principal, credentials);
		this.adminMode = adminMode;
	}

	public boolean isAdminMode() {
		return adminMode;
	}

	public void setAdminMode(boolean adminMode) {
		this.adminMode = adminMode;
	}

}
