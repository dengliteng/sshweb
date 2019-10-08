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


//定义拦截器
public class LoginHandler extends HandlerInterceptorAdapter{

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		//定义String resource = "" 是为了如果url地址乱写的,那下面判断的就为false
		String resource = "";  
		
		//判读handler是不是HandlerMethod的子类
		//为了拿到HandlerMapping
		if(handler instanceof HandlerMethod){ 
			//强转
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			//拿到当前访问的方法
			Method method = handlerMethod.getMethod();
			//拿到注解对象
			RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
			//当前访问的页面对应的权限标志
			resource = requestMapping.value()[0];
		}else{
			throw new RuntimeException("您获取的页面不存在!");
		}
		
		//拿到系统中所有要进行权限控制的方法,对应的权限控制的标志
		List<String> resources = (List<String>) request.getServletContext().getAttribute("allPerRes");
		
		//获取所有用户登录的权限
		Set<String> userAllPermissions = (Set<String>) session.getAttribute("userAllPermissions");
		
		User user = (User) session.getAttribute("loginUser");
		if(user == null){
			response.sendRedirect(request.getContextPath()+"/login");
		}else{
			boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
			if(!isAdmin && resources.contains(resource)){
				//进行权限控制的:对比两个东西userAllPermissions包不包含resource
				if(!userAllPermissions.contains(resource)){
					throw new RuntimeException("您没有权限访问该功能");
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	

	
	
}
