package cn.tedu.store.sshweb.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME) //指定定义的注解@AutoClass(value="")的生命周期
public @interface AutoClass {
	public String value() default "";
}
