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
		
		/*
			1.创建Spring的IOC容器对象
			ApplicationContext代表IOC容器
			ClassPathXmlApplicationContext是ApplicationContext接口的实现类。该实现类从类路径下加载配置文件。
		*/
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		/*
			2.从IOC容器中获取Bean实例
			利用id定位到IOC容器中的Bean
		*/
		HelloWorld hw = (HelloWorld) ctx.getBean("helloWorld");
		//调用hello方法
		hw.hello();
		
		Car car = (Car) ctx.getBean("car");
		System.out.println(car.toString());
		
	}

}