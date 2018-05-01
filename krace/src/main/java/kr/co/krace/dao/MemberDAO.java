package kr.co.krace.dao;


import org.springframework.orm.ibatis.SqlMapClientTemplate;

import kr.co.krace.vo.MemberVO;


public class MemberDAO extends BaseDAO {
	
	public MemberVO selectMemberByMemberName(String memberName) {
		SqlMapClientTemplate jt = getSqlMapClientTemplate();
		return (MemberVO)jt.queryForObject("select.member", memberName);
	}

}
