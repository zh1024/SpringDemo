package com.jimmy.spring.tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopDaoTest {

	private ApplicationContext ctx;
	private BookShopDao bookShopDao;
	
	{
		ctx = new ClassPathXmlApplicationContext("beans-tx.xml");
		bookShopDao = (BookShopDao) ctx.getBean("bookShopDao");
	}
	
	@Test
	public void test() {
//		System.out.println(bookShopDao.findBookPriceByIsbn("0001"));
		
		bookShopDao.updateBookStock("0001");
		
//		bookShopDao.updateAccount("AA", 50);
		
		
	}

}
