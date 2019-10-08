package cn.tedu.store.sshweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;
import cn.tedu.store.sshweb.model.Role;
import cn.tedu.store.sshweb.model.SystemContext;
import cn.tedu.store.sshweb.service.PermissionService;
import cn.tedu.store.sshweb.service.RolesService;
import cn.tedu.store.sshweb.web.AutoClass;
import cn.tedu.store.sshweb.web.AutoMethod;

@AutoClass
@Controller
public class RoleController {

	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private PermissionService permissionService;
	
	//获取数据的权限角色
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getAllRole",method=RequestMethod.POST )
	public List<Map<String, String>> getAllRole(){
	    List<Role> roles = rolesService.getAllRole();
	    List<Map<String,String>> rolesMap = new ArrayList<Map<String,String>>();	   
	    for(Role role : roles){
	     Map<String, String> map = new HashMap<String, String>();
	    	map.put("id", role.getId()+"");
	    	map.put("text", role.getRoleName());
	    	rolesMap.add(map);
	    }
	    
		return rolesMap;
	}
	
	
	//返回角色权限的页面
	@AutoMethod
	@RequestMapping(value="/getAllPagerRoles",method=RequestMethod.GET)
	public String getAllPagerRoles(){
		return "main_role";
	}
	
	
	//获取所有角色的权限
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getPagerRoles",method=RequestMethod.POST )
	public List<Map<String,String>> getPagerRoles(){
		List<Permission> permissions = permissionService.getPermissionDao();
		List<Map<String,String>> listPermission = new ArrayList<Map<String,String>>();
		for(Permission permission : permissions){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", permission.getId()+"");
			map.put("text", permission.getResource());
			listPermission.add(map);
		}		
		return listPermission;		
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getAllLimtPermission",method=RequestMethod.POST )
	public Pager<Role> getAllLimtPermission(Integer page,Integer rows){
		if(rows!=null&&rows>0) SystemContext.setPageSize(rows);
		if(rows!=null&&page>0) SystemContext.setPageOffset((page-1)*rows);
		Pager<Role> role = rolesService.getAllLimtPermission();
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		return role;
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/addPrgerRoles",method=RequestMethod.POST )
	public String addPrgerRoles(@RequestBody Role role){
		try {
			rolesService.add(role);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/updatePrgerRoles",method=RequestMethod.POST )
	public String updatePrgerRoles(@RequestBody Role role){
		try {
			rolesService.update(role);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/deletePrgerRoles",method=RequestMethod.POST )
	public String deletePrgerRoles(@RequestParam("ids[]") int[] ids){
		try {
			for(int i=0;i<ids.length;i++){
				rolesService.delete(ids[i]);
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
}







