package com.seven.chen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.chen.dao.IResourceDao;
import com.seven.chen.dao.IRoleDao;
import com.seven.chen.dao.IRoleResourceDao;
import com.seven.chen.dao.IUserDao;
import com.seven.chen.dao.IUserRoleDao;
import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;
import com.seven.chen.entity.RoleResource;
import com.seven.chen.entity.UserRole;
import com.seven.chen.service.RoleService;

@Service("roleServicer")
public class RoleServicerImpl implements RoleService {

	private IUserDao userDao;
	
	private IRoleDao roleDao;
	
	private IUserRoleDao userroleDao;
	
	private IResourceDao resourceDao;
	
	private IRoleResourceDao roleResourceDao;
	
	
	public IUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IUserRoleDao getUserroleDao() {
		return userroleDao;
	}

	@Autowired
	public void setUserroleDao(IUserRoleDao userroleDao) {
		this.userroleDao = userroleDao;
	}

	public IResourceDao getResourceDao() {
		return resourceDao;
	}

	@Autowired
	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public IRoleResourceDao getRoleResourceDao() {
		return roleResourceDao;
	}

	@Autowired
	public void setRoleResourceDao(IRoleResourceDao roleResourceDao) {
		this.roleResourceDao = roleResourceDao;
	}

	@Override
	public void add(Role role) {
		roleDao.addRole(role);
	}

	@Override
	public void delete(int id) {
		
		roleDao.delete(id);
		
	}

	@Override
	public Role load(int id) {
		
		return roleDao.load(id);
	}

	@Override
	public List<Role> list() {
		return roleDao.listRole();
	}

	@Override
	public void update(Role role) {
		
		
	}

	@Override
	public List<Role> listRole() {
		
		return roleDao.listRole();
	}

	@Override
	public UserRole loadUserRole(int uid, int roleId) {
		return userroleDao.loadUserRole(uid, roleId);

	}

	@Override
	public void addUserRole(int uid, int roleId) {
		userroleDao.addUserRole(uid, roleId);
		
	}

	@Override
	public void deleteUserRole(int uid, int roleId) {
		
		
		
	}

	@Override
	public void deleteUserRoles(int uid) {
		
		
	}

	@Override
	public List<Resource> listRoleResource(int roleId) {
		
		return resourceDao.listRoleResource(roleId);
	}

	@Override
	public void addRoleResource(int roleId, int resId) {
		
		roleResourceDao.addRoleResource(roleId, resId);
	}

	@Override
	public void deleteRoleResource(int roleId, int resId) {
		
		
	}

	@Override
	public RoleResource loadResourceRole(int roleId, int resId) {
		
		return roleResourceDao.loadResourceRole(roleId, resId);
	}

	
	
}
