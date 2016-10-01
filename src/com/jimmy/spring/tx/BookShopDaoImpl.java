package com.jimmy.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String sql = "SELECT price FROM book where isbn = ?";
		int price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return price;
	}

	@Override
	public void updateBookStock(String isbn) {
		int stock = findBookStockByIsbn(isbn);
		if (stock == 0){
			throw new BookStockException("库存不足");
		}
		
		String sql = "UPDATE book_stock SET stock = stock-1 WHERE isbn = ?";
		jdbcTemplate.update(sql, isbn);
	}

	@Override
	public void updateAccount(String username, int price) {
		int balance = findUsernameBalanceByUsername(username);
		if (balance < price){
			throw new UserAccountException("余额不足");
		}
		
		String sql = "UPDATE account SET balance = balance-? WHERE username = ?";
		jdbcTemplate.update(sql, price, username);
	}
	
	public int findBookStockByIsbn(String isbn){
		String sql = "SELECT stock FROM book_stock where isbn = ?";
		int stock = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return stock;
	}
	
	public int findUsernameBalanceByUsername(String username){
		String sql = "SELECT balance FROM account where username = ?";
		int balance = jdbcTemplate.queryForObject(sql, Integer.class, username);
		return balance;
	}

}
