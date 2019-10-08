package cn.tedu.store.sshweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.sshweb.model.Pager;
import cn.tedu.store.sshweb.model.Permission;
import cn.tedu.store.sshweb.model.SystemContext;
import cn.tedu.store.sshweb.service.PermissionService;
import cn.tedu.store.sshweb.web.AutoClass;
import cn.tedu.store.sshweb.web.AutoMethod;

@AutoClass
@Controller
public class PermissionController {

	
	@Autowired
	private PermissionService permissionService;
	
	@AutoMethod
	@RequestMapping("/getAllPagerPermissions")
	public String getAllPagerPermissions(){
		return "main_permission";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/getAllPermission",method=RequestMethod.POST)
	public Pager<Permission> getAllPermission(Integer page,Integer rows){
		if(rows!=null&&rows>0) SystemContext.setPageSize(rows);
		if(page!=null&&page>0) SystemContext.setPageOffset((page-1)*rows);
		Pager<Permission> permission = permissionService.getAllLimtPermission();
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return permission;
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/addPermissions",method=RequestMethod.POST)
	public String addPermissions(@RequestBody Permission permission){
		try {
			permissionService.add(permission);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/updatePermissions",method=RequestMethod.POST)
	public String updatePermissions(@RequestBody Permission permission){
		try {
			permissionService.update(permission);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AutoMethod
	@ResponseBody
	@RequestMapping(value="/deletePermissions",method=RequestMethod.POST)
	public String deletePermissions(@RequestParam("ids[]") int[] ids){
		try {
			for(int i=0;i<ids.length;i++){
				permissionService.delete(ids[i]);
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
}







