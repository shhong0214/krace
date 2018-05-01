package kr.co.krace.security;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

public class DatabasePasswordSecurerBean extends JdbcDaoSupport {
	protected final Logger logger = LoggerFactory.getLogger(getClass());	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public void secureDatabase() {
		getJdbcTemplate().query(
				"select a.membername, a.password" +
				"  from kra_member a" +
				" where a.passwordencoding = 'N'" +
				"   and a.deleted = 'N'", new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String username = rs.getString(1);
				String password = rs.getString(2);
				if(username != null && !"".equals(username) &&
						password != null && !"".equals(password) ) {
					SaltedUser user = (SaltedUser)userDetailsService.loadUserByUsername(username);
					String encodedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
					getJdbcTemplate().update("" +
							"update kra_member " +
							"   set password = ?, passwordencoding = ? " +
							" where membername = ? ", 
							encodedPassword, 
							"Y",
							user.getMemberName());
					logger.debug("[kra]Updating password for memberId : "+user.getMemberId()+" to: "+encodedPassword);					
				}
			}			
		});
	}
}
