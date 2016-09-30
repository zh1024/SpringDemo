package com.jimmy.spring.beans.aop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-aop.xml");
		
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");
		
		int result = arithmeticCalculator.add(2, 3);
		
		System.out.println(result);
		
		result = arithmeticCalculator.div(2, 2);
		
		System.out.println(result);
	}
		
}
