package cn.tedu.store.sshweb.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.tedu.store.sshweb.model.Permission;
import cn.tedu.store.sshweb.model.Role;
import cn.tedu.store.sshweb.model.User;
import cn.tedu.store.sshweb.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String LoginUser(String username,String password,HttpSession session){
		User loginUser = userService.login(username,password);
		//绑定登录的信息
		session.setAttribute("loginUser", loginUser);
		Set<Role> roles = loginUser.getRoles();
		boolean isAdmin = false;
		Set<Permission> permissions = new HashSet<Permission>();
		Set<String> userAllPermissions = new HashSet<String>();
		//判断是不是超级管理员,如果是则一切放行
		for(Role role : roles){
			if("超级管理员".equals(role.getRoleName())){
				isAdmin = true;
				continue;
			}
			//不是超级管理员的情况下,我们要把登录成功的用户,关联的所有权限标记取出来
			permissions = role.getPermissions();
			for(Permission permission : permissions){
				userAllPermissions.add(permission.getResource());	
			}			
		}
		//循环完毕后,userAllPermissions:包含登录成功的用户,所拥有的所有权限的标记
		session.setAttribute("isAdmin", isAdmin);
		if(!isAdmin){
			session.setAttribute("userAllPermissions", userAllPermissions);
		}
		return "redirect:/main";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping(value="/main_index",method=RequestMethod.GET)
	public String mainIndex(){
		return "main_index";
	}
	
	@RequestMapping("/getDatagridUsers")
	public String getDatagridUsers(){
		return "main_users";
	}
	
}
