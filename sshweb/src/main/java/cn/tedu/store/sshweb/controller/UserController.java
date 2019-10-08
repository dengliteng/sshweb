package cn.tedu.store.sshweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.base.Data;

import cn.tedu.store.sshweb.model.ComboBox;
import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Role;
import cn.tedu.store.sshweb.model.SystemContext;
import cn.tedu.store.sshweb.model.User;
import cn.tedu.store.sshweb.service.RolesService;
import cn.tedu.store.sshweb.service.UserService;
import cn.tedu.store.sshweb.util.SecurityUtil;
import cn.tedu.store.sshweb.web.AutoClass;
import cn.tedu.store.sshweb.web.AutoMethod;

@AutoClass
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RolesService rolesService;

	
	@AutoMethod
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@AutoMethod
	@RequestMapping(value="/getAllUser",method=RequestMethod.GET)
	public String getAllUser(Model model){
		List<User> list = userService.getAllUser();
		model.addAttribute("list", list);
		return "user";
	}
	
	@AutoMethod
	@RequestMapping(value="/getAllLimt",method=RequestMethod.GET)
	public String getAllLimt(Model model){
		SystemContext.setPageSize(2);
		SystemContext.setPageOffset(1);
		
		Pager<User> pager = userService.getAllLimtUser();
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		model.addAttribute("pager", pager);
		return "user";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping("/panel")
	public String panel(){
		return "{'name':'admin'}";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping("/comboboxtext")
	public String comboboxtext() throws JsonProcessingException{
		ComboBox[] arr = {new ComboBox(1,"aa"),new ComboBox(2,"bb"),new ComboBox(3,"cc")}; 
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(arr);
		System.out.println(json);
		return json;
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping("/comboboxtext1")
	public String comboboxtext1(@RequestParam int id) throws JsonProcessingException{
		ComboBox[] arr = {new ComboBox(2,"bb"),new ComboBox(3,"cc")}; 
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(arr);
		System.out.println(json);
		return json;
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getAllUserDatagrid",method=RequestMethod.POST)
	public List<User> getAllUserDatagrid(){
		List<User> list = userService.getAllUser();
		return list;
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getAllLimtDatagrid",method=RequestMethod.POST)
	public Pager<User> getAllLimtDatagrid(Integer page,Integer rows,
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="username",required=false) String username){
		if(rows!=null&&rows>0) SystemContext.setPageSize(rows);
		if(rows!=null&&page>0) SystemContext.setPageOffset((page-1)*rows);
		User user = new User();
		if(id!=null&&id>0) user.setId(id);
		if(username!=null&&!"".equals(username)) user.setUsername(username);
		Pager<User> pager = userService.getAllLimt(user);
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		
		return pager;
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getRoles",method=RequestMethod.POST)
	public  List<Role> getRoles(){
		List<Role> roles = rolesService.getAllRole();
		return roles;
	}
	
	//easyui修改数据
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/updatUser",method=RequestMethod.POST)
	public String updatUser(@RequestBody User user){
		
		userService.update(user);
		return "success";
	}
	
	//easyui权限控制 提交
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(String username,
						  String password,
						  String state,
						  String regDate,
						  String roles) throws ParseException{
		System.out.println("============"+roles);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setState(Integer.parseInt(state));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse(regDate);
		user.setRegDate(date);
		
		String[] role = roles.split(",");
		Set<Role> roleSet = new HashSet<Role>();
		int rol_id = 0;
		for(String rol : role){
			rol_id = Integer.parseInt(rol);
			Role ro = rolesService.load(rol_id);
			roleSet.add(ro);
		}
		user.setRoles(roleSet);
		try {
			userService.add(user);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/addUser_1",method=RequestMethod.POST)
	public String addUser(@RequestBody User user){
		String username = user.getUsername();
		String password = user.getPassword();
		user.setPassword(SecurityUtil.md5(username, password));
		try {
			userService.add(user);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public String updateUser(@RequestBody User user){
		
		try {
			userService.UpdataUser(user);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	public String deleteUser(@RequestParam("ids[]") int[] ids){
		System.out.println(ids);
		try {
			for(int i=0;i<ids.length;i++){
				userService.delete(ids[i]);
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
}






