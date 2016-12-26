package com.seven.chen.dao;

import com.seven.chen.entity.RoleResource;

public interface IRoleResourceDao extends BaseDao<RoleResource>{

	public void addRoleResource(int roleId,int resId);
	public void deleteRoleResource(int roleId,int resId);
	public RoleResource loadResourceRole(int roleId,int resId);
	
}
