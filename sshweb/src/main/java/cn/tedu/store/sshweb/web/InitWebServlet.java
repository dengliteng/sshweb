package cn.tedu.store.sshweb.web;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.tedu.store.sshweb.service.PermissionService;

public class InitWebServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	//两个初始化,一个初始化:spring的IOC容器的引用初始化到InitwebServlet类的一个静态方法
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void init() throws ServletException {
		//拿到全局作用范围的作用域
		ServletContext context = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
	
		List<String> resources = new ArrayList<String>();
		
		try {
			//第二个初始化,初始化权限
			//packageName实施权限控制的包全面
			String packageName = "cn.tedu.store.sshweb.controller";
			String PackageNamePath = packageName.replace(".", "/");
			//拿到PackageNamePath,进一步的获取到对应的服务器上磁盘的绝对路径
			String packageNameRealPath = this.getClass().getClassLoader().getResource(PackageNamePath).getPath();
			File file = new File(packageNameRealPath);
			//遍历这个文件夹你的文件
			String[] classFileNames = file.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if(name.endsWith(".class")){
						return true;
					}
					return false;
				}
			});
			
			for(String classFileName : classFileNames){
				//.class这个后缀给删除到
				classFileName = classFileName.substring(0, classFileName.indexOf(".class"));
				//拿到纯粹的类的包全名
				String classAllpackageName = packageName+ "."+classFileName;
				//拿到纯粹的类的包全名,可以通过他们获取到对应的类的对象,通过反射
				Class clazz = Class.forName(classAllpackageName);
				//拿到这些controller的对象,获取到在他们身上的注解
				if(!clazz.isAnnotationPresent(AutoClass.class)) continue;
				//剩下的类,都是有@AuthClass这个注解的类,这些类都要进行权限控制的
				//拿到这些类的所有方法
				Method[] methods =clazz.getDeclaredMethods(); //包含private
				for(Method method : methods){
					if(!method.isAnnotationPresent(AutoMethod.class)) continue;
					//都是有@AutoMethod的方法,拿到要保存到Permission表里resource字段的值
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					resources.add(requestMapping.value()[0]);		
				}
			}
			//List<String> resources:包含了controller包里,所有被@AuthClass和@AutoMethod共同作用的
			//方法上面的@RequestMapping的value值,都在里面
			PermissionService permissionService = (PermissionService) applicationContext.getBean("permissionService");
			permissionService.initPermission(resources);
			context.setAttribute("allPerRes", resources);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}














