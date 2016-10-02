package com.jimmy.spring.tx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier {

	@Autowired
	private BookShopService bookShopService;
	
	/*
	 * 当 bookShopService的 purchase()方法被另一个事务方法 checkout()调用时, 
	 * 它默认会在现有的事务内运行. 这个默认的传播行为就是 REQUIRED. 
	 * 因此在 checkout()方法的开始和终止边界内只有一个事务. 这个事务只在 checkout()方法结束的时候被提交.
	 * 
	 * 另一种常见的传播行为是 REQUIRES_NEW. 
	 * 它表示该方法必须启动一个新事务, 并在自己的事务内运行. 如果有事务在运行, 就应该先挂起它.
	*/
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void checkout(List<String> isbns, String username) {
		for (String isbn : isbns) {
			bookShopService.purchase(isbn, username);
		}
	}

}
