package com.seven.chen.controller;

import java.awt.List;
import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.seven.chen.entity.User;
import com.seven.chen.service.RoleService;
import com.seven.chen.service.UserService;
import com.seven.chen.util.ShiroKit;

@RequestMapping("/")
@Controller
public class LoginController {

	private UserService userService;
	
	private RoleService roleService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		ArrayList  list = new ArrayList();
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String emsg = null;
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			emsg = e.getMessage();
		}
		if(ShiroKit.isEmpty(emsg)) {
			return "redirect:/admin/user/list";
		} else {
			model.addAttribute("emsg", emsg);
			return "/login";
		}
	}
	
}
