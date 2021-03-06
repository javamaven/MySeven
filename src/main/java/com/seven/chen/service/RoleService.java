package com.seven.chen.service;

import java.util.List;

import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;
import com.seven.chen.entity.RoleResource;
import com.seven.chen.entity.UserRole;


public interface RoleService {
	public void add(Role role);
	
	public void delete(int id);
	
	public Role load(int id);
	
	public List<Role> list();
	
	public void update(Role role);
	
	public List<Role> listRole();
	
	
	public UserRole loadUserRole(int uid,int roleId);
	
	public void addUserRole(int uid,int roleId);
	
	public void deleteUserRole(int uid,int roleId);
	
	/**
	 * 删除某个用户的所有角色
	 * @param uid
	 */
	public void deleteUserRoles(int uid);
	/**
	 * 根据角色id获取可以访问的所有资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> listRoleResource(int roleId);
	
	public void addRoleResource(int roleId,int resId);
	
	public void deleteRoleResource(int roleId,int resId);
	
	public RoleResource loadResourceRole(int roleId,int resId);
}
