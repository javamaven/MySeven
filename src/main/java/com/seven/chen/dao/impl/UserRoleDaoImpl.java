package com.seven.chen.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seven.chen.dao.IUserRoleDao;
import com.seven.chen.entity.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements IUserRoleDao{
	public UserRole loadUserRole(int uid,int roleId){
		
		String sql= "select * from tb_userrole where uid = ? and roleId = ? ";
		
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		params.add(roleId);
		return this.get(sql, params,  new RowMapper<UserRole>(){
	
		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			UserRole userrole = new UserRole();
			
			userrole.setId(rs.getInt("id"));
			userrole.setUserId(rs.getInt("userId"));
			userrole.setRoleId(rs.getInt("roleId"));
			return userrole;
		}
		
	});
		
	}

	@Override
	public void addUserRole(int uid, int roleId) {
		// TODO Auto-generated method stub
		String sql = "insert into tb_userrole (userId,roleId) values (?,?)";
		List<Object> params = new ArrayList <Object>();
		params.add(uid);
		params.add(roleId);
		this.update(sql, params);
	}

	@Override
	public void deleteUserRole(int uid, int roleId) {

		
	}

	@Override
	public void deleteUserRoles(int uid) {
		// TODO Auto-generated method stub
		
	}
	
	

}