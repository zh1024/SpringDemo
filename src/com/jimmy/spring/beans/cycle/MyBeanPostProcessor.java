package com.jimmy.spring.beans.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	//在初始化方法之前被调用
	//Object arg0:Bean的实例
	//String arg1:Bean实例的ID
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("postProcessBeforeInitialization..." + arg0 + " " + arg1);
		return arg0;
	}
	
	@Override
	//在初始化方法之后被调用
	//Object arg0:Bean的实例
	//String arg1:Bean实例的ID
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("postProcessAfterInitialization..." + arg0 + " " + arg1);
		if (arg1.equals("car")) {
			((Car)arg0).setBrand("Ford");
		}
		return arg0;
	}

}
