package cn.tedu.store.sshweb.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SshwebTest {

	@Test
	public void testJdbc(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		System.out.println(sessionFactory);
		System.out.println(sessionFactory.openSession());
	}
	
}
