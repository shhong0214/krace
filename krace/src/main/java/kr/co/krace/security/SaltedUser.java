package kr.co.krace.security;


import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SaltedUser extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6858004326744104127L;
	
	private long memberId;
	private String empCode;
	private String memberName;
	private long companyId;
	private Date salt;
	
	public SaltedUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, 
			long memberId, String memberName,  Date salt) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.memberId = memberId;
		this.memberName = memberName;
		this.salt = salt;
	}

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

	public Date getSalt() {
		return salt;
	}

	public void setSalt(Date salt) {
		this.salt = salt;
	}
	
}
