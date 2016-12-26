package com.seven.chen.dao;

import java.util.List;

import com.seven.chen.entity.Role;

public interface IRoleDao extends BaseDao<Role> {

	public void addRole(Role role);
	
	public List<Role> listRole();
	
	public List<Role> listUserRole(int uid);

	public List<Role> listRoleSnByUser(int uid);	
	
	public void delete(int id);
	
	public Role load(int id);
	
}
