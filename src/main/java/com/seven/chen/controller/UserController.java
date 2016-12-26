package com.seven.chen.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;
import com.seven.chen.entity.User;
import com.seven.chen.service.ResourceService;
import com.seven.chen.service.RoleService;
import com.seven.chen.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	private UserService userService;
	
	private RoleService roleService;
	
	private ResourceService resourceService;
	
	private User user;
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	
	
	public ResourceService getResourceService() {
		return resourceService;
	}


	@Autowired
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * �˺� �б�
	 * 
	 * @param pager
	 * @param user
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("user/list");
		SecurityManager securityManager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("kh","kh");
		try{
			
		}catch(UnknownAccountException e){
			
			
			System.out.println("用户名不存在");
			
		}catch(IncorrectCredentialsException ee){
			System.out.println("密码不存在");
		}

		return mav;
	}	
/*	Role r = new Role();
	r.setName("测试组");
	roleService.add(r);
	
	Resource res = new Resource();
	res.setName("系统管理");
	res.setUrl("/admin/*");
	resourceService.add(res);
	
	res = new Resource();
	res.setName("用户管理");
	res.setUrl("/admin/user/*");
	resourceService.add(res);
	
	res = new Resource();
	res.setName("用户添加");
	res.setUrl("/admin/user/add");
	resourceService.add(res);
	
	res = new Resource();
	res.setName("用户删除");
	res.setUrl("/admin/user/delete");
	resourceService.add(res);
	
	res = new Resource();
	res.setName("角色管理");
	res.setUrl("/admin/role/*");
	resourceService.add(res);
	
	res = new Resource();
	res.setName("角色添加");
	res.setUrl("/admin/role/add");
	resourceService.add(res);
	res = new Resource();
	res.setName("角色修改");
	res.setUrl("/admin/role/update");
	resourceService.add(res);
	
	User u = new User();
	u.setUsername("张三");
	u.setNickname("zhangsan");
	u.setStatus(1);
	u.setPassword("111");
	userService.add(u);
	u.setUsername("管理员");
	u.setNickname("admin");
	u.setStatus(1);
	u.setPassword("111");
	userService.add(u);			
	System.out.println(subject.getPrincipal());
	*/
	@RequestMapping("getuser/{id}")
	public String getUser(@PathVariable("id")int id,Map<String,Object> map){
    	user = userService.load(id);
/*    	model.addAttribute("user", user);
    	model.addAttributeuser("msg", "失败");*/
    	map.put("user", user);
    	return "login";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
