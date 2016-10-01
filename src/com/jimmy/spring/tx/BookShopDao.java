package com.jimmy.spring.tx;

public interface BookShopDao {
	
	public int findBookPriceByIsbn(String isbn);
	
	public void updateBookStock(String isbn);
	
	public void updateAccount(String username, int price);
	
}
