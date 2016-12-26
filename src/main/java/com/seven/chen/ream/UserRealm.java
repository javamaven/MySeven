package com.seven.chen.ream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.remoting.caucho.SimpleBurlapServiceExporter;
import org.springframework.stereotype.Component;

import com.seven.chen.entity.Resource;
import com.seven.chen.entity.User;
import com.seven.chen.service.UserService;

public class UserRealm extends AuthorizingRealm{

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	//作用户授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user = ((User)principals.getPrimaryPrincipal());
		System.out.println(user.getUsername()+user.getNickname());
		List<String> roles = userService.listRoleSnByUser(user.getId());
		List<Resource> reses = userService.listAllResource(user.getId());
		List<String> permissions = new ArrayList<String>();
		for(Resource r:reses) {
			permissions.add(r.getUrl());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(new HashSet<String>(roles));
		info.setStringPermissions(new HashSet<String>(permissions));
		return info;
	}

	
	//作用户验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		User user = new User();
		try{
		user = userService.login(username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		SimpleAuthenticationInfo Info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		Info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
		return Info;
	}

}
