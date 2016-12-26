package com.seven.chen.ream;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MapRealm implements Realm{

	public static Map<String,String> users;
	
	static{
		
		users = new HashMap<String,String>();
		users.put("123", "123");
		users.put("456", "456");
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "mapRealm";
	}

	
	
	@Override
	public boolean supports(AuthenticationToken token) {
		// TODO Auto-generated method stub
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = token.getPrincipal().toString();
		
		String password = new String((char[])token.getCredentials());
		
		//此处可查询数据库查看用户名密码是否匹配
		if(!users.containsKey(username)) throw new UnknownAccountException("用户名出错");
		if(!users.get(username).equals(users.get(username))) throw new IncorrectCredentialsException("密码错误");
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
		
		System.out.println(username+password);
		return info;
	}
	
	

}
