package com.seven.chen.dao;

import java.util.List;

import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;


public interface IResourceDao extends BaseDao<Resource>{
	
	public List<Resource> listResource();
	
	public List<Resource> listAllResource(int uid);
	
	
	public List<Resource> listRoleResource(int roleId);
	
	public void add(Resource res);
	
	public Resource load(int id);
	
}
