package cn.tedu.store.sshweb.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME) //ָ�������ע��@AutoClass(value="")����������
public @interface AutoClass {
	public String value() default "";
}
