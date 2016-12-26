package com.seven.chen.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seven.chen.dao.BaseDao;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T>{

	protected JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public long count(String sql) {
		Long result = jdbcTemplate.queryForObject(sql, Long.class);
		return result == null ? 0 : result;
	}
	
	public long count(String sql, List<Object> params) {
		Long result = jdbcTemplate.queryForObject(sql, setValues(params), Long.class);
		return result == null ? 0 : result;
	}
	
	public List<T> find(String sql, List<Object> params, RowMapper<T> rowMapper) {
		List<T> list = jdbcTemplate.query(sql, setValues(params), rowMapper);
		return null == list ? new ArrayList<T>() : list;
	}
	
	public List<T> find(String sql, RowMapper<T> rowMapper) {
		List<T> list = jdbcTemplate.query(sql, rowMapper);
		return null == list ? new ArrayList<T>() : list;
	}
	
	public boolean check(String sql, List<Object> params) {
		long result = count(sql, params);
		return result > 0;
	}
	
	protected Object[] setValues(List<Object> params) {
		if (params == null) {
			return new Object[]{};
		}
		Object[] args = new Object[params.size()];
		for (int i = 0; i < params.size(); ++i) {
			args[i] = params.get(i);
		}
		return args;
	}
	
	public Object findFieldById(String sql, List<Object> params) {
		Object obj = null;
		try {
			obj = jdbcTemplate.queryForObject(sql, Object.class, setValues(params));
		} catch (DataAccessException e) {
		}
		return obj;
	}
	
	public T get(String sql, List<Object> params, RowMapper<T> rowMapper) {
		List<T> list = this.find(sql, params, rowMapper);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	public List<Object[]> find(String sql, Object[] params, RowMapper<Object[]> rowMapper) {
		params = params == null ? new Object[]{} : params;
		List<Object[]> list = jdbcTemplate.query(sql, params, rowMapper);
		return null == list ? new ArrayList<Object[]>() : list;
	}

	public void update(String sql, List<Object> params) {
		jdbcTemplate.update(sql, setValues(params));
	}	
	
}
