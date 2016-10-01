package com.jimmy.spring.tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopServiceTest {

	private ApplicationContext ctx;
	private BookShopService bookShopService;
	
	{
		ctx = new ClassPathXmlApplicationContext("beans-tx.xml");
		bookShopService = (BookShopService) ctx.getBean("bookShopService");
	}
	
	@Test
	public void test() {
		bookShopService.purchase("0001", "AA");
	}

}
