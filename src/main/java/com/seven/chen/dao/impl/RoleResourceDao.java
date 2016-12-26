package com.seven.chen.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seven.chen.dao.IRoleResourceDao;
import com.seven.chen.entity.RoleResource;

@Repository("roleResourceDao")
public class RoleResourceDao extends BaseDaoImpl<RoleResource> implements IRoleResourceDao{

	@Override
	public void addRoleResource(int roleId, int resId) {

		String sql = "insert into  tb_roleresource (roleId,resourceId) values(?,?)";
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(roleId);
		params.add(resId);
		
		this.update(sql, params);
		
	}

	@Override
	public void deleteRoleResource(int roleId, int resId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleResource loadResourceRole(int roleId, int resId) {
		
		String sql = "select * from tb_roleresource where roleId=? and resourceId=?  ";
		List<Object> params = new ArrayList<Object>();
		
		params.add(roleId);
		params.add(resId);
		
		return this.get(sql, params, new RowMapper<RoleResource>(){

			@Override
			public RoleResource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				RoleResource re = new RoleResource();
				re.setId(rs.getInt("id"));
				re.setResourceId(rs.getInt("resourceId"));
				re.setRoleId(rs.getInt("roleId"));
				return re;
			}});
	}

}
