package kr.co.krace.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.krace.common.KRaceConstants;
import kr.co.krace.dao.MemberDAO;
import kr.co.krace.exception.KRaceException;
import kr.co.krace.vo.MemberVO;

@Service
public class KRaceUserDetailsService implements UserDetailsService {
	protected Logger logger = Logger.getLogger(getClass());
	public static final String DELEMETER_USERNAME = ":";
	
	@Autowired
	MemberDAO memberDAO;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		logger.debug("loadUserByUsername : username[" + username + "]");

		boolean adminMode = true;
		MemberVO member = memberDAO.selectMemberByMemberName(username);
		if(member != null) {
	        Date salt = member.getCreateDate();
	        String memberId = String.valueOf(member.getMemberId());
	        
	        return new SaltedUser(username, member.getPassword(), true, true, true, true, 
	        		getAuthorities(username, member.getMemberType(), adminMode), 
	        		member.getMemberId(), member.getMemberName(), salt);
		} else {
			throw new KRaceAuthenticationException(
					KRaceException.UNREGISTERED_USER_ERROR, "UNREGISTERED_USER_ERROR", null);
		}
	}	
	
	public Collection<GrantedAuthority> getAuthorities(String userName, String memberType, boolean adminMode) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		addAuthority(authorities, KRaceConstants.ROLE_ADMIN);

		return authorities;
	}

	private void addAuthority(List<GrantedAuthority> authorities, String role) {
        GrantedAuthorityImpl authority = new GrantedAuthorityImpl(role);
        authorities.add(authority);
	}

}
