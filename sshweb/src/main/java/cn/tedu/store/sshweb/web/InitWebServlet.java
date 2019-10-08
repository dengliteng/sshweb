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

	//������ʼ��,һ����ʼ��:spring��IOC���������ó�ʼ����InitwebServlet���һ����̬����
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void init() throws ServletException {
		//�õ�ȫ�����÷�Χ��������
		ServletContext context = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
	
		List<String> resources = new ArrayList<String>();
		
		try {
			//�ڶ�����ʼ��,��ʼ��Ȩ��
			//packageNameʵʩȨ�޿��Ƶİ�ȫ��
			String packageName = "cn.tedu.store.sshweb.controller";
			String PackageNamePath = packageName.replace(".", "/");
			//�õ�PackageNamePath,��һ���Ļ�ȡ����Ӧ�ķ������ϴ��̵ľ���·��
			String packageNameRealPath = this.getClass().getClassLoader().getResource(PackageNamePath).getPath();
			File file = new File(packageNameRealPath);
			//��������ļ�������ļ�
			String[] classFileNames = file.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if(name.endsWith(".class")){
						return true;
					}
					return false;
				}
			});
			
			for(String classFileName : classFileNames){
				//.class�����׺��ɾ����
				classFileName = classFileName.substring(0, classFileName.indexOf(".class"));
				//�õ��������İ�ȫ��
				String classAllpackageName = packageName+ "."+classFileName;
				//�õ��������İ�ȫ��,����ͨ�����ǻ�ȡ����Ӧ����Ķ���,ͨ������
				Class clazz = Class.forName(classAllpackageName);
				//�õ���Щcontroller�Ķ���,��ȡ�����������ϵ�ע��
				if(!clazz.isAnnotationPresent(AutoClass.class)) continue;
				//ʣ�µ���,������@AuthClass���ע�����,��Щ�඼Ҫ����Ȩ�޿��Ƶ�
				//�õ���Щ������з���
				Method[] methods =clazz.getDeclaredMethods(); //����private
				for(Method method : methods){
					if(!method.isAnnotationPresent(AutoMethod.class)) continue;
					//������@AutoMethod�ķ���,�õ�Ҫ���浽Permission����resource�ֶε�ֵ
					RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
					resources.add(requestMapping.value()[0]);		
				}
			}
			//List<String> resources:������controller����,���б�@AuthClass��@AutoMethod��ͬ���õ�
			//���������@RequestMapping��valueֵ,��������
			PermissionService permissionService = (PermissionService) applicationContext.getBean("permissionService");
			permissionService.initPermission(resources);
			context.setAttribute("allPerRes", resources);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}














