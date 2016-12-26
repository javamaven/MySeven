package com.seven.chen.ream;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class StaticRealm extends AuthorizingRealm{

	//用来判断授权的
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//用来判断认证的
		String username = token.getPrincipal().toString();
		
		String password = new String((char[])token.getCredentials());
		
		//此处可查询数据库查看用户名密码是否匹配
		if(!"kh".equals(username)) throw new UnknownAccountException("用户名出错");
		if(!"kh".equals(password)) throw new IncorrectCredentialsException("密码错误");
		
		AuthenticationInfo info = new SimpleAuthenticationInfo("254906593",password,getName());
		
		return info;
	}

}
