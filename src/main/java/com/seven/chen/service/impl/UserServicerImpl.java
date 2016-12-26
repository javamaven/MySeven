package com.seven.chen.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.chen.dao.IResourceDao;
import com.seven.chen.dao.IRoleDao;
import com.seven.chen.dao.IRoleResourceDao;
import com.seven.chen.dao.IUserDao;
import com.seven.chen.dao.IUserRoleDao;
import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;
import com.seven.chen.entity.User;
import com.seven.chen.service.UserService;
import com.seven.chen.util.ShiroKit;

@Service("userService")
public class UserServicerImpl implements UserService {

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
	public void add(User user) {
		if(ShiroKit.isEmpty(user.getUsername())||ShiroKit.isEmpty(user.getPassword())) {
			throw new RuntimeException("用户名或者密码不能为空！");
		}
		user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
		userDao.adduser(user);
	}

	@Override
	public void add(User user, List<Integer> rids) {
		this.add(user);
		for(int rid:rids) {
			userroleDao.addUserRole(user.getId(), rid);
		}
		
	}

	@Override
	public void delete(int id) {

		userDao.delete(id);
	}

	@Override
	public void update(User user, List<Integer> rids) {

		
		
	}

	@Override
	public void update(User user) {

		
	}

	@Override
	public User load(int id) {

		return userDao.load(id);
	}

	@Override
	public User loadByUsername(String username) {

		return userDao.loadByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		System.out.println("...................");
		User u = userDao.loadByUsername(username);
		if(u==null) throw new UnknownAccountException("用户名或者密码出错");
		if(!u.getPassword().equals(ShiroKit.md5(password, username)))
			throw new IncorrectCredentialsException("用户名或者密码出错");
		if(u.getStatus()==0) throw new LockedAccountException("用户已经被锁定");
		return u;
	}

	@Override
	public List<User> list() {
		return userDao.listUser();
	}

	@Override
	public List<User> listByRole(int id) {
		return userDao.listByRole(id);
	}

	@Override
	public List<Resource> listAllResource(int uid) {
		return resourceDao.listAllResource(uid);
	}

	@Override
	public List<String> listRoleSnByUser(int uid) {
		List<String> datas = new ArrayList<String>();
		
		for(Role data : roleDao.listRoleSnByUser(uid)){
			datas.add(data.getSn());
		}
		
		return datas;
	}

	@Override
	public List<Role> listUserRole(int uid) {
		return roleDao.listUserRole(uid);
	}

}
