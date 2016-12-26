package com.seven.chen.dao;

import com.seven.chen.entity.UserRole;

public interface IUserRoleDao extends BaseDao<UserRole> {
	public UserRole loadUserRole(int uid,int roleId);
	
	public void addUserRole(int uid,int roleId);
	
	public void deleteUserRole(int uid,int roleId);
	
	/**
	 * 删除某个用户的所有角色
	 * @param uid
	 */
	public void deleteUserRoles(int uid);
	
}
