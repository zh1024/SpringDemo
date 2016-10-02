package com.jimmy.spring.tx;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CashierTest {

	private ApplicationContext ctx;
	private Cashier cashier;
	
	{
		ctx = new ClassPathXmlApplicationContext("beans-tx.xml");
		cashier = (Cashier) ctx.getBean("cashier");
	}
	
	@Test
	public void test() {
		cashier.checkout(Arrays.asList("0001","0002"), "AA");
	}

}
