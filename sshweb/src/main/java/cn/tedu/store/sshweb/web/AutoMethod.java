package cn.tedu.store.sshweb.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)  //ָ������AutoMethod(value="")����������
public @interface AutoMethod {
	public String value() default ""; 
}
