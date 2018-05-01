package kr.co.krace.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.krace.exception.KRaceException;


public class KRaceAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	@Autowired
	KRaceUserDetailsService userDetailsService;
	@Autowired	
	MessageDigestPasswordEncoder passwordEncoder;

	@Override
	public boolean supports(Class<? extends Object> authentication) {
        return (KRaceAuthenticationToken.class.isAssignableFrom(authentication));
	}	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		SaltedUser saltedUser = (SaltedUser)userDetails;
		if (  passwordEncoder.isPasswordValid(userDetails.getPassword(), 
				(String) authentication.getCredentials(), saltedUser.getSalt()) == false ) {
			throw new KRaceAuthenticationException(
					KRaceException.CHECK_PASSWORD_ERROR, "CHECK_PASSWORD_ERROR", null);
		}
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		KRaceAuthenticationToken token = (KRaceAuthenticationToken)authentication;	
		SaltedUser user = (SaltedUser)userDetailsService.loadUserByUsername(username);
		return user;
	}

}
