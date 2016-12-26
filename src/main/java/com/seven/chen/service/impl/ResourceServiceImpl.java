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
import com.seven.chen.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{

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
	public void add(Resource res) {
		
		resourceDao.add(res);
	}

	@Override
	public void update(Resource res) {
		
		
	}

	@Override
	public void delete(int id) {
		
		
	}

	@Override
	public Resource load(int id) {
		
		return resourceDao.load(id);
	}

	@Override
	public List<Resource> listResource() {
		
		return resourceDao.listResource();
	}

}
