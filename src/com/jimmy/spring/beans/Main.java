package com.jimmy.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		/*//创建HelloWorld实例
		HelloWorld hw = new HelloWorld();
		//对Name属性赋值
		hw.setName("Jimmy");
		//调用hello方法
		hw.hello();*/
		
		//创建Spring的IOC容器对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从IOC容器中获取Bean实例
		HelloWorld hw = ctx.getBean(HelloWorld.class);
		//调用hello方法
		hw.hello();
		
	}

}