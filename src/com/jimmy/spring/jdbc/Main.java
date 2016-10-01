package com.jimmy.spring.jdbc;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-jdbc.xml");
		
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		
		System.out.println(dataSource);
	}

}
