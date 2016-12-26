package com.seven.chen.service;

import java.util.List;

import com.seven.chen.entity.Resource;

public interface ResourceService {

	public void add(Resource res);
	
	public void update(Resource res);
	
	public void delete(int id);
	
	public Resource load(int id);
	
	public List<Resource> listResource();
	
}
