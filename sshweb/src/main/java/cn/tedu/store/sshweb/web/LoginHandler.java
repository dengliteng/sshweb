package cn.tedu.store.sshweb.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.tedu.store.sshweb.model.User;


//����������
public class LoginHandler extends HandlerInterceptorAdapter{

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		//����String resource = "" ��Ϊ�����url��ַ��д��,�������жϵľ�Ϊfalse
		String resource = "";  
		
		//�ж�handler�ǲ���HandlerMethod������
		//Ϊ���õ�HandlerMapping
		if(handler instanceof HandlerMethod){ 
			//ǿת
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			//�õ���ǰ���ʵķ���
			Method method = handlerMethod.getMethod();
			//�õ�ע�����
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			//��ǰ���ʵ�ҳ���Ӧ��Ȩ�ޱ�־
			resource = requestMapping.value()[0];
		}else{
			throw new RuntimeException("����ȡ��ҳ�治����!");
		}
		
		//�õ�ϵͳ������Ҫ����Ȩ�޿��Ƶķ���,��Ӧ��Ȩ�޿��Ƶı�־
		List<String> resources = (List<String>) request.getServletContext().getAttribute("allPerRes");
		
		//��ȡ�����û���¼��Ȩ��
		Set<String> userAllPermissions = (Set<String>) session.getAttribute("userAllPermissions");
		
		User user = (User) session.getAttribute("loginUser");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login");
		}else{
			boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
			if(!isAdmin && resources.contains(resource)){
				//����Ȩ�޿��Ƶ�:�Ա���������userAllPermissions��������resource
				if(!userAllPermissions.contains(resource)){
					throw new RuntimeException("��û��Ȩ�޷��ʸù���");
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	

	
	
}
