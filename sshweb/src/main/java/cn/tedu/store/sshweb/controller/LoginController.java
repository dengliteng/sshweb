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
		//�󶨵�¼����Ϣ
		session.setAttribute("loginUser", loginUser);
		Set<Role> roles = loginUser.getRoles();
		boolean isAdmin = false;
		Set<Permission> permissions = new HashSet<Permission>();
		Set<String> userAllPermissions = new HashSet<String>();
		//�ж��ǲ��ǳ�������Ա,�������һ�з���
		for(Role role : roles){
			if("��������Ա".equals(role.getRoleName())){
				isAdmin = true;
				continue;
			}
			//���ǳ�������Ա�������,����Ҫ�ѵ�¼�ɹ����û�,����������Ȩ�ޱ��ȡ����
			permissions = role.getPermissions();
			for(Permission permission : permissions){
				userAllPermissions.add(permission.getResource());	
			}			
		}
		//ѭ����Ϻ�,userAllPermissions:������¼�ɹ����û�,��ӵ�е�����Ȩ�޵ı��
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
