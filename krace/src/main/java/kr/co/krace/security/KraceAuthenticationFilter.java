package kr.co.krace.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class KraceAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		logger.debug("[cam]attemptAuthentication params : " +
				"j_username[" + request.getParameter("j_username") + "], " +
				"j_password[" + request.getParameter("j_password") + "], " +
				"j_adminmode[" + request.getParameter("j_adminmode") + "], " +
				"j_memberid[" + request.getParameter("j_memberid") + "]");	
		
		String username;
		String password;
		String adminMode;
		String memberId;		
		
		password = request.getParameter("j_password");
		memberId = request.getParameter("j_memberid");
		adminMode = request.getParameter("j_adminmode");
		username = request.getParameter("j_username");
		
		KRaceAuthenticationToken token = 
			new KRaceAuthenticationToken(username, password, true);
		return this.getAuthenticationManager().authenticate(token);
	}

}
