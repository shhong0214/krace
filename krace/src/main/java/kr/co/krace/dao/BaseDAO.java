package kr.co.krace.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BaseDAO extends SqlMapClientDaoSupport {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
}
