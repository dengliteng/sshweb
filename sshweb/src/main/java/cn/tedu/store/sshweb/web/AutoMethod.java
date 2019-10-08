package cn.tedu.store.sshweb.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)  //指定定义AutoMethod(value="")的生命周期
public @interface AutoMethod {
	public String value() default ""; 
}
