package com.seven.chen.dao;

import java.util.List;

import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;
import com.seven.chen.entity.User;

public interface IUserDao extends BaseDao<User>{

	/**
	 * 增加用户信息.
	 * @param user
	 */
	public void adduser(User user);
	
	public List<User> listUser();
	
	public User loadByUsername(String username);
	
	public List<User> listByRole(int id);
	
	public User load(int id);
	
	public void delete(int id);

}
